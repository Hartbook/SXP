/* Uncomment this file if you have a recent version of firefox installed
// Only tested on Windows

package controller;
 
import static org.junit.Assert.*;
 
import java.awt.List;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;
 
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
 
public class SeleniumTest {
   
    private WebDriver driver;
   
    private String s;
   
    private String workingDir;
   
    private File pageAcueil;
   
    @Before
    public void setUp() throws Exception {
       
        driver = new FirefoxDriver();
       
        workingDir = System.getProperty("user.dir");
       
        s = File.separator;
       
        System.out.println("dir : "+workingDir);
       
        String page = workingDir + s + "src"+s+"main"+s+"js"+s+"html"+s+"index.html";
       
        System.out.println("pageAcueil : "+page);
       
        pageAcueil = new File(page);
       
        System.out.println("pageAcueil getAbsolutePath : "+pageAcueil.getAbsolutePath());
       
        driver.get(pageAcueil.getAbsolutePath());
       
       
       
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); }
   
    @After
    public void tearDown() throws Exception {
        driver.close();
    }
       
    @Test //1
    public void testLoginPage() {
       
        driver.get(pageAcueil.getAbsolutePath());
       
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        WebElement NameInput=null;
        WebElement PasswordInput =null;
        try{
            NameInput = driver.findElement(By.id("login"));
            PasswordInput =driver.findElement(By.id("password"));
        } catch(Exception e){fail("login password not found");}
        NameInput.sendKeys("Amina");
        PasswordInput.sendKeys("123456");
        WebElement button = null;
        try{
            button = driver.findElement(By.cssSelector("div.form-group button.btn-default"));
        }catch(Exception e){fail("button not found");}
        WebElement buttonsubscribe = null;
        try{
            button = driver.findElement(By.cssSelector("div.ng-scope h1.ng-scope"));
        }catch(Exception e){e.printStackTrace();fail("buttonsubscribe not found");}
        WebElement Home = null;
        try{
            Home = driver.findElement(By.id("sidebar-wrapper" ));
        }catch(Exception e){fail("Home not found");}
    }
   
    @Test //2
    public void testAddItemPage() {
            String pathToHome = pageAcueil.toURI()+"#myitems/add";
            driver.get(pathToHome);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
           
        WebElement titreInput = null;
        WebElement descrInput = null;
           
        try {    
            titreInput = driver.findElement(By.id("title"));
            descrInput = driver.findElement(By.id("description"));
        } catch(Exception e) {fail("GUI Element not found");}
       
        titreInput.sendKeys("DELL");
        descrInput.sendKeys("pc portable");
        WebElement buttonsubmit = null;
        try{
         driver.findElement(By.cssSelector("div.form-group button.btn-default"));}
        catch(Exception e) { fail(" Buttonsubmit not found");}
         WebElement buttoncancel = null;
            try{
                buttoncancel = driver.findElement(By.cssSelector("div.form-group button.btn-warning"));
             }catch(Exception e){fail("buttoncancel not found");}
        WebElement Items = null;
        try{
            Items = driver.findElement(By.id("sidebar-wrapper"));
        }catch(Exception e){fail("Items not found");}}

        @Test //3
    public void testSearchPage() {
            String pathToHome = pageAcueil.toURI()+"#search";
            driver.get(pathToHome);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            WebElement searchInput = null;
            try{
                searchInput = driver.findElement(By.cssSelector("div.input-group  input.form-control "));
            }catch(Exception e){fail("searchInput not found");}
            searchInput.sendKeys("ordi");
            WebElement button = null;
            try{
             button = driver.findElement(By.cssSelector("div.ng-scope button.btn-default"));
            }catch(Exception e){fail("buttonsearch not found");}
            WebElement Search = null;
            try{
                Search = driver.findElement(By.cssSelector("div.nav span.btn-lg"));
            }catch(Exception e){fail("Search  not found");}
     }
       
   
   
   
    @Test //4
    public void testMessagesPage() {
    String pathToHome = pageAcueil.toURI()+"#messages";
        driver.get(pathToHome);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement msgInput= null;
        try{
            msgInput = driver.findElement(By.cssSelector("div.well input.form-control"));
            }catch(Exception e){fail("msgtext not found");}
        msgInput.sendKeys("Carrots have finished my friend");
        WebElement button = null;
        try{
            button = driver.findElement(By.cssSelector("div.input-group button.btn-default"));
            }catch(Exception e){fail("buttonmsg not found");}
        WebElement Messages = null;
        try{
            Messages = driver.findElement(By.cssSelector("div.nav span.btn-lg "));
        }catch(Exception e){fail("Messages not found");}
	}
   
   
    @Test //5
    public void testSettingsPage() {
    String pathToHome = pageAcueil.toURI()+"#settings";
        driver.get(pathToHome);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement msgInput = null;
        try{
        msgInput = driver.findElement(By.cssSelector("div.ng-scope input.ng-valid"));
        }catch(Exception e){fail("button not found");}
        WebElement Settings = null;
        try{
            Settings = driver.findElement(By.cssSelector("sidebar-wrapper "));
        }catch(Exception e){fail("Settings not found");}
     }
 
 
    @Test //6
        public void testAccountPage() {
        String pathToHome = pageAcueil.toURI()+"#account";
            driver.get(pathToHome);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            WebElement keyInput =null;
            try{
            keyInput = driver.findElement(By.id("comment"));
            }catch(Exception e){fail("button not found");}
            keyInput.sendKeys("7880000");
            WebElement Account =null;
            try{
            Account = driver.findElement(By.id("sidebar-wrapper"));
            }catch(Exception e){fail("Account not found");}
         }
 
   
    @Test //7
    public void testLogoutPage() {
    String pathToHome = pageAcueil.toURI()+"#logout";
        driver.get(pathToHome);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertEquals(true, true);
        WebElement Logout =null;
        try{
             Logout = driver.findElement(By.cssSelector("div.ng-scope p.ng-scope "));
        }catch(Exception e){fail("Logout not found");}
     }
 
 
@Test //8
public void ContractPage() {
String pathToHome = pageAcueil.toURI()+"#contract";
    driver.get(pathToHome);
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    assertEquals(true, true);
    WebElement Contract =null;
    try{
        Contract = driver.findElement(By.id("sidebar-wrapper"));
    }catch(Exception e){fail("Contract not found");}
 }
 
   
    @Test //9
    public void AboutPage() {
    String pathToHome = pageAcueil.toURI()+"#about";
        driver.get(pathToHome);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertEquals(true, true);
        WebElement Logout =null;
        try{
             Logout = driver.findElement(By.id("sidebar-wrapper"));
        }catch(Exception e){fail("About not found");}}
 }

*/
