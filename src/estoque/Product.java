package estoque;

import java.util.Locale;

public abstract class Product implements Vendavel {
    private final String nome;
    private double preco;
    private int quantidade;

    protected Product(String nome, double preco, int quantidade)
            throws QuantidadeInvalidaException {
        if (preco < 0 || !Double.isFinite(preco)) {
            throw new QuantidadeInvalidaException("O preço não pode ser negativo ou inválido.");
        }
        if (quantidade < 0) {
            throw new QuantidadeInvalidaException("A quantidade não pode ser negativa.");
        }
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório.");
        }

        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public abstract double calcularValorTotal();

    public String getDescricao() {
        return String.format(
                Locale.forLanguageTag("pt-BR"),
                "%s | Preço: R$ %.2f | Quantidade: %d",
                nome,
                preco,
                quantidade);
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public void vender(int quantidadeDesejada) throws ProdutoIndisponivelException {
        if (quantidadeDesejada <= 0) {
            throw new ProdutoIndisponivelException("A quantidade da venda deve ser maior que zero.");
        }
        if (quantidadeDesejada > quantidade) {
            throw new ProdutoIndisponivelException(
                    "Estoque insuficiente para " + nome + ". Disponível: " + quantidade + ".");
        }
        quantidade -= quantidadeDesejada;
    }

    public void aplicarDesconto(double percentual) {
        aplicarDesconto(percentual, preco);
    }

    public void aplicarDesconto(double percentual, double descontoMaximo) {
        if (!Double.isFinite(percentual) || percentual < 0 || percentual > 100) {
            throw new IllegalArgumentException("O percentual deve estar entre 0 e 100.");
        }
        if (!Double.isFinite(descontoMaximo) || descontoMaximo < 0) {
            throw new IllegalArgumentException("O desconto máximo deve ser um valor não negativo.");
        }

        double valorDesconto = Math.min(preco * percentual / 100, descontoMaximo);
        preco -= valorDesconto;
    }
}