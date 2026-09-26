package estoque;

import java.util.Locale;

public class EstoqueApp {
    public static void main(String[] args) {
        Estoque estoque = new Estoque();

        try {
            ProdutoComum caderno = new ProdutoComum("Caderno", 100.00, 5);
            caderno.aplicarDesconto(10);
            estoque.adicionarProduto(caderno);

            ProdutoComum caneta = new ProdutoComum("Caneta", 20.00, 10);
            caneta.aplicarDesconto(25, 2.00);
            estoque.adicionarProduto(caneta);

            estoque.adicionarProduto(new ProdutoPerecivel("Iogurte", 3.50, 10, 2));
            estoque.adicionarProduto(new ProdutoPerecivel("Arroz", 25.00, 4, 30));

            new ProdutoComum("Produto inválido", 10.00, -1);
        } catch (QuantidadeInvalidaException e) {
            System.out.println("Quantidade inválida capturada: " + e.getMessage());
        }

        System.out.println("Produtos cadastrados:");
        for (Product produto : estoque.getProdutos()) {
            System.out.println("- " + produto.getDescricao());
        }

        try {
            estoque.venderProduto(0, 2);
            System.out.println("Venda realizada: 2 unidades de Caderno.");
        } catch (ProdutoIndisponivelException e) {
            System.out.println("Produto indisponível: " + e.getMessage());
        }

        try {
            estoque.venderProduto(0, 100);
        } catch (ProdutoIndisponivelException e) {
            System.out.println("Produto indisponível capturado: " + e.getMessage());
        }

        System.out.printf(
                Locale.forLanguageTag("pt-BR"),
                "Valor total do estoque: R$ %.2f%n",
                estoque.calcularValorTotalEstoque());
    }
}