package estoque;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Estoque {
    private final List<Product> produtos = new ArrayList<>();

    public void adicionarProduto(Product produto) {
        produtos.add(Objects.requireNonNull(produto, "O produto não pode ser nulo."));
    }

    public void venderProduto(int indice, int quantidade) throws ProdutoIndisponivelException {
        produtos.get(indice).vender(quantidade);
    }

    public double calcularValorTotalEstoque() {
        double valorTotal = 0;
        for (Product produto : produtos) {
            valorTotal += produto.calcularValorTotal();
        }
        return valorTotal;
    }

    public List<Product> getProdutos() {
        return Collections.unmodifiableList(new ArrayList<>(produtos));
    }
}