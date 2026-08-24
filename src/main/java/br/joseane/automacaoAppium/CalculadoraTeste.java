package br.joseane.automacaoAppium;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CalculadoraTeste {
    public static void main(String[] args) throws MalformedURLException {
        // Cria as opções de configuração da sessão do Appium.
        // Essas opções dizem ao Appium qual dispositivo, sistema operacional e engine usar.
        UiAutomator2Options options = new UiAutomator2Options();

        // Identifica o emulador Android que será usado.
        // O valor "emulator-5554" é o identificador do dispositivo emulado.
        options.setDeviceName("emulator-5554");

        // Diz que o sistema operacional alvo é Android.
        options.setPlatformName("Android");

        // Usa o driver de automação UIAutomator2 do Android.
        // Ele é o mecanismo oficial para testar apps Android em dispositivos/emuladores.
        options.setAutomationName("UiAutomator2");

        // Lê parâmetros informados na linha de comando, como:
        // -Dapp.path=C:\caminho\para\app.apk
        // -Dapp.package=com.google.android.calculator
        // -Dapp.activity=com.android.calculator2.Calculator
        String appPath = System.getProperty("app.path");
        String appPackage = System.getProperty("app.package", "com.google.android.calculator");
        String appActivity = System.getProperty("app.activity", "com.android.calculator2.Calculator");

        // Se você passar um APK, o Appium instala e executa esse app.
        // Isso é útil quando a app não está pré-instalada no emulador.
        if (appPath != null && !appPath.isBlank()) {
            Path apk = Paths.get(appPath);

            // Valida se o arquivo existe antes de tentar usar.
            if (!Files.exists(apk)) {
                throw new IllegalArgumentException("Arquivo APK não encontrado: " + apk);
            }

            // Define o caminho do APK para o Appium abrir e rodar.
            options.setApp(appPath);
        } else {
            // Se não houver APK, o Appium tenta abrir uma app já instalada no emulador.
            // appPackage = nome do pacote da aplicação Android.
            // appActivity = classe principal da tela inicial da aplicação.
            options.setAppPackage(appPackage);
            options.setAppActivity(appActivity);

            // noReset(true) faz o Appium não limpar/resetar o estado da app.
            // Isso ajuda quando a app já está instalada e você quer reutilizar aquela instalação.
            options.setNoReset(true);

            // Exibe no console qual app está sendo usada.
            System.out.println("Usando appPackage=" + appPackage + " appActivity=" + appActivity);
        }

        // Cria o driver do Appium apontando para o servidor local do Appium.
        // A URL do Appium geralmente fica em http://127.0.0.1:4723/
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);

        // Aqui entra o seu código de automação:
        // - localizar elementos
        // - clicar
        // - inserir texto
        // - validar resultados
        // Exemplo: driver.findElement(...)
        WebElement el1 = driver.findElement(AppiumBy.accessibilityId("2"));
        el1.click();
        WebElement el2 = driver.findElement(AppiumBy.accessibilityId("plus"));
        el2.click();
        WebElement el3 = driver.findElement(AppiumBy.accessibilityId("2"));
        el3.click();
        WebElement el4 = driver.findElement(AppiumBy.accessibilityId("equals"));
        el4.click();

        // Fecha a sessão do Appium ao final do teste.
        // Isso libera o emulador e fecha a conexão com o servidor Appium.
        driver.quit();
    }
}

