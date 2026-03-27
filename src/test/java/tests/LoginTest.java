package tests;

import data.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.LoginPage;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class LoginTest extends BaseTest {

    LoginPage loginPage = new LoginPage();

    // Cenários de login (usuário, senha, deveLogar, mensagemEsperada)
    private static Stream<Arguments> cenariosLogin() {
        /** Cenários de login que vou validar (positivo e negativo)
         * */
        return Stream.of(
                Arguments.of(UserData.STANDARD_USER, UserData.PASSWORD, true, ""),
                Arguments.of(UserData.PROBLEM_USER, UserData.PASSWORD, true, ""),
                Arguments.of(UserData.PERFORMANCE_GLITCH_USER, UserData.PASSWORD, true, ""),
                Arguments.of(UserData.ERROR_USER, UserData.PASSWORD, true, ""),
                Arguments.of(UserData.VISUAL_USER, UserData.PASSWORD, true, ""),
                Arguments.of(UserData.LOCKED_OUT_USER, UserData.PASSWORD, false, "Epic sadface: Sorry, this user has been locked out."),
                Arguments.of("usuarioInexistente", "senhaInvalida", false, "Epic sadface: Username and password do not match any user in this service"),
                Arguments.of("", "", false, "Epic sadface: Username is required"),
                Arguments.of("", UserData.PASSWORD, false, "Epic sadface: Username is required"),
                Arguments.of(UserData.STANDARD_USER, "", false, "Epic sadface: Password is required")
        );
    }

    @ParameterizedTest(name = "Login com usuário {0}")
    @MethodSource("cenariosLogin")
    void testLogin(String usuario, String senha, boolean deveLogar, String mensagemEsperada) {
        // tenta logar com os dados do cenário, definido no cenariosLogin()
        loginPage.tentarLogin(usuario, senha);

        if (deveLogar) {
            // positivo:  login deu certo
            Assertions.assertTrue(loginPage.estaNaPaginaDeProdutos(),
                    "Login falhou para o usuário válido: " + usuario);
        } else {
            // negativo: valida mensagem exibida
            Assertions.assertEquals(mensagemEsperada, loginPage.getMensagemErro(),
                    "Mensagem de erro incorreta para o usuário: " + usuario);
            loginPage.fecharMensagemErro();
        }
    }
}
