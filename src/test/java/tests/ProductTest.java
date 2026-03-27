package tests;

import pages.LoginPage;
import pages.ProductPage;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static core.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class ProductTest extends BaseTest {

    LoginPage loginPage;
    ProductPage produtoPage;

    @BeforeEach
    void setupPages() {
        loginPage = new LoginPage();
        produtoPage = new ProductPage();
    }

    @ParameterizedTest(name = "t01_Menu com usuário {0}")
    @MethodSource("data.UsuariosData#todosUsuarios")
    void t01_deveAbrirMenu(String usuario, String senha) {
        loginPage.tentarLogin(usuario, senha);
        produtoPage.abrirMenu();
        produtoPage.fecharMenu();
    }

    @ParameterizedTest(name = "t02_AllItems com usuário {0}")
    @MethodSource("data.UsuariosData#todosUsuarios")
    void t02_deveAbrirItensDoMenuAllItems(String usuario, String senha) {
        loginPage.tentarLogin(usuario, senha);
        produtoPage.itensDoMenuAllItems();
    }

    @ParameterizedTest(name = "t03_About com usuário {0}")
    @MethodSource("data.UsuariosData#todosUsuarios")
    void t03_deveAbrirItensDoMenuAbout(String usuario, String senha) {
        loginPage.tentarLogin(usuario, senha);
        produtoPage.itensDoMenuAbout();
        Assertions.assertTrue(getDriver().getCurrentUrl().contains("saucelabs"));
    }

    @ParameterizedTest(name = "t04_Logout com usuário {0}")
    @MethodSource("data.UsuariosData#todosUsuarios")
    void t04_deveAbrirItensDoMenuLogout(String usuario, String senha) {
        loginPage.tentarLogin(usuario, senha);
        produtoPage.itensDoMenuLogout();
    }

    @ParameterizedTest(name = "t05_ResetAppState com usuário {0}")
    @MethodSource("data.UsuariosData#todosUsuarios")
    void t05_deveAbrirItensDoMenuResetAppState(String usuario, String senha) {
        loginPage.tentarLogin(usuario, senha);
        produtoPage.itensDoMenuResetAppState();
    }

    @ParameterizedTest(name = "t06_Ordenação com usuário {0}")
    @MethodSource("data.UsuariosData#usuariosValidos")
    void t06_testOrdenacao(String usuario, String senha) {
        loginPage.tentarLogin(usuario, senha);
        produtoPage.ordenarSelecionarAtoZ();
        produtoPage.ordenarSelecionarZtoA();
        produtoPage.ordenarPorPriceBaixoAoAlto();
        produtoPage.ordenarPorPriceAltoAoBaixo();
    }

    @ParameterizedTest(name = "t07_Ordenação falha com usuário {0}")
    @MethodSource("data.UsuariosData#usuariosProblematicos")
    void t07_ordenacaoFalha(String usuario, String senha) {
        loginPage.tentarLogin(usuario, senha);
        produtoPage.ordenarSelecionarAtoZ();
        Assertions.assertFalse(produtoPage.isOrdenacaoAplicada(),
                "Usuário problemático deveria falhar na ordenação");
    }

    @ParameterizedTest(name = "t08_Carrinho com usuário válido {0}")
    @MethodSource("data.UsuariosData#usuariosValidos")
    void t08_deveAdicionarERemoverProduto(String usuario, String senha) {
        loginPage.tentarLogin(usuario, senha);
        produtoPage.adicionarProdutoAoCarrinho("sauce-labs-backpack");
        assertEquals("1", produtoPage.getQuantidadeCarrinho());

        produtoPage.removerProdutoDoCarrinho("sauce-labs-backpack");
        assertEquals("", produtoPage.getQuantidadeCarrinho(),
                "Usuário válido deveria conseguir remover produto");
    }

    @ParameterizedTest(name = "t09_Carrinho com usuário problemático {0}")
    @MethodSource("data.UsuariosData#usuariosProblematicos")
    void t09_deveFalharRemoverProduto(String usuario, String senha) {
        loginPage.tentarLogin(usuario, senha);
        produtoPage.adicionarProdutoAoCarrinho("sauce-labs-backpack");
        assertEquals("1", produtoPage.getQuantidadeCarrinho());

        produtoPage.removerProdutoDoCarrinho("sauce-labs-backpack");
        Assertions.assertNotEquals("", produtoPage.getQuantidadeCarrinho(),
                "Usuário problemático não deveria conseguir remover produto");
    }

    @ParameterizedTest(name = "t10_MultiplosProdutos com usuário válido {0}")
    @MethodSource("data.UsuariosData#usuariosValidos")
    void t10_deveAdicionarMultiplosProdutosAoCarrinho(String usuario, String senha) {
        loginPage.tentarLogin(usuario, senha);
        produtoPage.adicionarMultiplosProdutosAoCarrinho(
                "sauce-labs-bike-light",
                "sauce-labs-bolt-t-shirt",
                "sauce-labs-fleece-jacket",
                "sauce-labs-onesie",
                "test.allthethings()-t-shirt-(red)"
        );
        assertEquals("5", produtoPage.getQuantidadeCarrinho());
    }

    @ParameterizedTest(name = "t11_MultiplosProdutos com usuário problemático {0}")
    @MethodSource("data.UsuariosData#usuariosProblematicos")
    void t11_deveFalharAdicionarMultiplosProdutos(String usuario, String senha) {
        loginPage.tentarLogin(usuario, senha);
        produtoPage.adicionarMultiplosProdutosAoCarrinho(
                "sauce-labs-bike-light",
                "sauce-labs-bolt-t-shirt",
                "sauce-labs-fleece-jacket",
                "sauce-labs-onesie",
                "test.allthethings()-t-shirt-(red)"
        );
        Assertions.assertNotEquals("5", produtoPage.getQuantidadeCarrinho(),
                "Usuário problemático não deveria conseguir adicionar todos os produtos");
    }
}
