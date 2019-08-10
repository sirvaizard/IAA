class BacktrackingHorse {
    static final int tamanhoTabuleiro = 8;
    static final int[][] jogadasPossiveis = {{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};
    static int[][] tabuleiro = new int[tamanhoTabuleiro][tamanhoTabuleiro];
    static int[] posAtual = {0, 0};
    static int jogada = 1;
    public static void main(String[] args) {
        tabuleiro[0][0] = jogada;
        if(movimentarCavalo()) System.out.println("Existe solucao.");
        else System.out.println("Nao existe solucao.");
        imprimirTabuleiro();
    }

    static void imprimirTabuleiro() {
        for(int x = 0; x < tamanhoTabuleiro; x++) {
            for(int y = 0; y < tamanhoTabuleiro; y++) System.out.printf("%d ", tabuleiro[x][y]);
            System.out.println();
        }
    }

    static boolean isFull() {
        for(int x = 0; x < tamanhoTabuleiro; x++) {
            for(int y = 0; y < tamanhoTabuleiro; y++) {
                if(tabuleiro[x][y] == 0) return false;
            }
        }

        return true;
    }

    static boolean isFree(int x, int y) {
        if(x > tamanhoTabuleiro-1 || y > tamanhoTabuleiro-1 || x < 0 || y < 0) return false;
        if(tabuleiro[x][y] == 0) return true;
        return false;
    }

    static boolean movimentarCavalo() {
        int[][] tempTabuleiro = tabuleiro.clone();
        int[] tempPosAtual = posAtual.clone();
        for(int x = 0; x < jogadasPossiveis.length; x++) {
            int posX = posAtual[0] + jogadasPossiveis[x][0];
            int posY = posAtual[1] + jogadasPossiveis[x][1];
            if(isFree(posX, posY)) {
                posAtual[0] = posX; 
                posAtual[1] = posY;
                tabuleiro[posX][posY] = ++jogada;
                movimentarCavalo();
            }
        }
        tabuleiro = tempTabuleiro.clone();
        posAtual = tempPosAtual.clone();
        if(isFull()) return true;
        else return false;
    }
}