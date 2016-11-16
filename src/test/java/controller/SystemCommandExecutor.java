package controller;

import java.io.*;
import java.util.List;

public class SystemCommandExecutor
{

class ThreadedStreamHandler extends Thread
{
  InputStream inputStream;
  String adminPassword;
  OutputStream outputStream;
  PrintWriter printWriter;
  StringBuilder outputBuffer = new StringBuilder();
  private boolean sudoIsRequested = false;
  
  ThreadedStreamHandler(InputStream inputStream)
  {
    this.inputStream = inputStream;
  }

  ThreadedStreamHandler(InputStream inputStream, OutputStream outputStream, String adminPassword)
  {
    this.inputStream = inputStream;
    this.outputStream = outputStream;
    this.printWriter = new PrintWriter(outputStream);
    this.adminPassword = adminPassword;
    this.sudoIsRequested = true;
  }
  
  public void run()
  {
    if (sudoIsRequested)
    {
      printWriter.println(adminPassword);
      printWriter.flush();
    }

    BufferedReader bufferedReader = null;
    try
    {
      bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
      String line = null;
      while ((line = bufferedReader.readLine()) != null)
      {
        outputBuffer.append(line + "\n");
      }
    }
    catch (IOException ioe)
    {
      ioe.printStackTrace();
    }
    catch (Throwable t)
    {
      t.printStackTrace();
    }
    finally
    {
      try
      {
        bufferedReader.close();
      }
      catch (IOException e)
      {
      }
    }
  }
  
  private void doSleep(long millis)
  {
    try
    {
      Thread.sleep(millis);
    }
    catch (InterruptedException e)
    {
    }
  }
  
  public StringBuilder getOutputBuffer()
  {
    return outputBuffer;
  }

}

  private List<String> commandInformation;
  private String adminPassword;
  private ThreadedStreamHandler inputStreamHandler;
  private ThreadedStreamHandler errorStreamHandler;
  
  public SystemCommandExecutor(final List<String> commandInformation)
  {
    if (commandInformation==null) throw new NullPointerException("The commandInformation is required.");
    this.commandInformation = commandInformation;
    this.adminPassword = null;
  }

  public int executeCommand()
  throws IOException, InterruptedException
  {
    int exitValue = -99;

    try
    {
      ProcessBuilder pb = new ProcessBuilder(commandInformation);
      Process process = pb.start();

      OutputStream stdOutput = process.getOutputStream();
      
      InputStream inputStream = process.getInputStream();
      InputStream errorStream = process.getErrorStream();

      inputStreamHandler = new ThreadedStreamHandler(inputStream, stdOutput, adminPassword);
      errorStreamHandler = new ThreadedStreamHandler(errorStream);

      inputStreamHandler.start();
      errorStreamHandler.start();

      exitValue = process.waitFor();
 
      inputStreamHandler.interrupt();
      errorStreamHandler.interrupt();
      inputStreamHandler.join();
      errorStreamHandler.join();
    }
    catch (IOException e)
    {
      throw e;
    }
    catch (InterruptedException e)
    {
      throw e;
    }
    finally
    {
      return exitValue;
    }
  }

  public StringBuilder getStandardOutputFromCommand()
  {
    return inputStreamHandler.getOutputBuffer();
  }

  public StringBuilder getStandardErrorFromCommand()
  {
    return errorStreamHandler.getOutputBuffer();
  }
}

