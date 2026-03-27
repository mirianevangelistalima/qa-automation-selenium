package tests;

import pages.LoginPage;
import pages.ProductDetailsPage;
import pages.ProductPage;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static core.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class ProductDetailsTest extends BaseTest {

    LoginPage loginPage;
    ProductPage produtoPage;
    ProductDetailsPage produtoDetalhesPage;

    String nomeProduto = "Test.allTheThings() T-Shirt (Red)";

    @BeforeEach
    void setupPages() {
        loginPage = new LoginPage();
        produtoPage = new ProductPage();
        produtoDetalhesPage = new ProductDetailsPage();
    }


    @ParameterizedTest(name = "t01_Detalhes com usuário válido {0}")
    @MethodSource("data.UsuariosData#usuariosQueAbremDetalheCorreto")
    void t01_deveAbrirDetalhesDoProdutoValido(String usuario, String senha) {
        loginPage.tentarLogin(usuario, senha);
        produtoDetalhesPage.clicarNoTituloEAbrirMaisDetalhe(nomeProduto);
        Assertions.assertTrue(getDriver().getCurrentUrl().contains("/inventory-item.html?id=3"));
    }

    @ParameterizedTest(name = "t02_Detalhes incorretos com usuário {0}")
    @MethodSource("data.UsuariosData#usuariosProblemUser")
    void t02_deveFalharAbrirDetalhesDoProduto(String usuario, String senha) {
        loginPage.tentarLogin(usuario, senha);
        produtoDetalhesPage.clicarNoTituloEAbrirMaisDetalhe(nomeProduto);
        Assertions.assertFalse(getDriver().getCurrentUrl().contains("/inventory-item.html?id=3"));

    }

    @ParameterizedTest(name = "t03_Add carrinho com usuário válido {0}")
    @MethodSource("data.UsuariosData#usuariosValidos")
    void t03_deveAdicionarProdutoAoCarrinhoValido(String usuario, String senha) {
        loginPage.tentarLogin(usuario, senha);
        produtoDetalhesPage.clicarNoTituloEAbrirMaisDetalhe(nomeProduto);

        produtoDetalhesPage.adicionarProdutoAoCarrinho();
        Assertions.assertTrue(produtoDetalhesPage.isBotaoRemoveVisivel(),
                "Usuário válido deveria ver botão Remove");
        assertEquals("1", produtoPage.getQuantidadeCarrinho());
    }

    @ParameterizedTest(name = "t04_Add carrinho falha com usuário error_user")
    @MethodSource("data.UsuariosData#usuariosError")
    void t04_deveFalharAdicionarProdutoAoCarrinhoError(String usuario, String senha) {
        loginPage.tentarLogin(usuario, senha);
        produtoDetalhesPage.clicarNoTituloEAbrirMaisDetalhe(nomeProduto);

        produtoDetalhesPage.adicionarProdutoAoCarrinho();

        Assertions.assertFalse(produtoDetalhesPage.isBotaoRemovePresente(),
                "error_user não deveria conseguir adicionar ao carrinho");
        Assertions.assertNotEquals("1", produtoPage.getQuantidadeCarrinho(),
                "error_user não deveria atualizar carrinho corretamente");
    }


    @ParameterizedTest(name = "t05_Remove carrinho com usuário válido {0}")
    @MethodSource("data.UsuariosData#usuariosValidos")
    void t05_deveRemoverProdutoDoCarrinhoValido(String usuario, String senha) {
        loginPage.tentarLogin(usuario, senha);
        produtoDetalhesPage.clicarNoTituloEAbrirMaisDetalhe(nomeProduto);

        produtoDetalhesPage.adicionarProdutoAoCarrinho();
        produtoDetalhesPage.removerProdutoDoCarrinho();
        assertEquals("", produtoPage.getQuantidadeCarrinho());
    }

    @ParameterizedTest(name = "t06_Remove carrinho falha com problem_user {0}")
    @MethodSource("data.UsuariosData#usuariosProblemUser")
    void t06_deveFalharRemoverProdutoDoCarrinhoProblem(String usuario, String senha) {
        loginPage.tentarLogin(usuario, senha);
        produtoDetalhesPage.clicarNoTituloEAbrirMaisDetalhe(nomeProduto);

        produtoDetalhesPage.adicionarProdutoAoCarrinho();
        produtoDetalhesPage.removerProdutoDoCarrinho();
        Assertions.assertNotEquals("", produtoPage.getQuantidadeCarrinho(),
                "problem_user não deveria conseguir remover produto");
    }

    @ParameterizedTest(name = "t07_Remove carrinho falha com error_user {0}")
    @MethodSource("data.UsuariosData#usuariosError")
    void t07_deveFalharRemoverProdutoDoCarrinhoError(String usuario, String senha) {
        loginPage.tentarLogin(usuario, senha);
        produtoDetalhesPage.clicarNoTituloEAbrirMaisDetalhe(nomeProduto);

        produtoDetalhesPage.adicionarProdutoAoCarrinho();

        Assertions.assertFalse(produtoDetalhesPage.isBotaoRemovePresente(),
                "error_user não deveria conseguir remover produto");
    }

    @ParameterizedTest(name = "t08_Back to products com usuário {0}")
    @MethodSource("data.UsuariosData#todosUsuarios")
    void t08_deveClicarNoBotaoBackToProducts(String usuario, String senha) {
        loginPage.tentarLogin(usuario, senha);
        produtoDetalhesPage.clicarNoTituloEAbrirMaisDetalhe(nomeProduto);

        produtoDetalhesPage.clicarNoBotaoBackToProducts();
        Assertions.assertTrue(getDriver().getCurrentUrl().contains("/inventory.html"));
    }
}
