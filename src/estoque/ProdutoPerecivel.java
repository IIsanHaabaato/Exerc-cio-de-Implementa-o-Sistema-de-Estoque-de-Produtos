package estoque;

import java.util.Locale;

public class ProdutoPerecivel extends Product {
    private final int diasParaVencer;

    public ProdutoPerecivel(String nome, double preco, int quantidade, int diasParaVencer)
            throws QuantidadeInvalidaException {
        super(nome, preco, quantidade);
        if (diasParaVencer < 0) {
            throw new QuantidadeInvalidaException("Os dias para vencer não podem ser negativos.");
        }
        this.diasParaVencer = diasParaVencer;
    }

    @Override
    public double calcularValorTotal() {
        double valorTotal = getPreco() * getQuantidade();
        return diasParaVencer <= 3 ? valorTotal * 0.8 : valorTotal;
    }

    @Override
    public String getDescricao() {
        return String.format(
                Locale.forLanguageTag("pt-BR"),
                "%s | Validade: %d dia(s)",
                super.getDescricao(),
                diasParaVencer);
    }

    public int getDiasParaVencer() {
        return diasParaVencer;
    }
}