class Figura {
    int[][] forma;
    int columna;
    int[] alturasMaximas;
    int[] alturasMinimas;

    Figura(int id, int rotacion, int columna) {
        this.forma = getMatrix(id, rotacion);
        this.columna = columna;
        alturasMaximas = new int[4];
        alturasMinimas = new int[4];
        // O(1)
        for (int i = 0; i < 4; i++)
            alturasMaximas[i] = alturaMaximaCol(i);
        for (int i = 0; i < 4; i++)
            alturasMinimas[i] = alturaMinimaCol(i);
    }

    private int alturaMaximaCol(int col) {
        int altura = 0;
        for (int fila = 0; fila < 4; fila++)
            if (forma[fila][col] == 1)
                return 4 - fila;
        return 0;
    }

    public int alturaMinimaCol(int col) {
        for (int fila = 3; fila >= 0; fila--)
            if (forma[fila][col] == 1)
                return 4 - fila;
        return 0;
    }

    // el mejor metodo que he escrito en mi carrera profesional
    public static int[][] getMatrix(int id, int rotation) {
        switch (id) {
            case 1:
                switch (rotation) {
                    case 0:
                    case 180:
                        return new int[][] {
                                { 0, 0, 0, 0 },
                                { 0, 0, 0, 0 },
                                { 0, 0, 0, 0 },
                                { 1, 1, 1, 1 }
                        };
                    case 90:
                    case 270:
                        return new int[][] {
                                { 1, 0, 0, 0 },
                                { 1, 0, 0, 0 },
                                { 1, 0, 0, 0 },
                                { 1, 0, 0, 0 }
                        };
                }
            case 2:
                switch (rotation) {
                    case 0:
                        return new int[][] {
                                { 0, 0, 0, 0 },
                                { 0, 0, 0, 0 },
                                { 1, 0, 0, 0 },
                                { 1, 1, 1, 0 }
                        };
                    case 90:
                        return new int[][] {
                                { 0, 0, 0, 0 },
                                { 0, 1, 0, 0 },
                                { 0, 1, 0, 0 },
                                { 1, 1, 0, 0 }
                        };
                    case 180:
                        return new int[][] {
                                { 0, 0, 0, 0 },
                                { 0, 0, 0, 0 },
                                { 1, 1, 1, 0 },
                                { 0, 0, 1, 0 }
                        };
                    case 270:
                        return new int[][] {
                                { 0, 0, 0, 0 },
                                { 1, 1, 0, 0 },
                                { 1, 0, 0, 0 },
                                { 1, 0, 0, 0 }
                        };
                }
            case 3:
                switch (rotation) {
                    case 0:
                        return new int[][] {
                                { 0, 0, 0, 0 },
                                { 0, 0, 0, 0 },
                                { 0, 0, 1, 0 },
                                { 1, 1, 1, 0 }
                        };
                    case 90:
                        return new int[][] {
                                { 0, 0, 0, 0 },
                                { 1, 1, 0, 0 },
                                { 0, 1, 0, 0 },
                                { 0, 1, 0, 0 }
                        };
                    case 180:
                        return new int[][] {
                                { 0, 0, 0, 0 },
                                { 0, 0, 0, 0 },
                                { 1, 1, 1, 0 },
                                { 1, 0, 0, 0 }
                        };
                    case 270:
                        return new int[][] {
                                { 0, 0, 0, 0 },
                                { 1, 0, 0, 0 },
                                { 1, 0, 0, 0 },
                                { 1, 1, 0, 0 }
                        };
                }
            case 4:
                switch (rotation) {
                    case 0:
                    case 90:
                    case 180:
                    case 270:
                        return new int[][] {
                                { 0, 0, 0, 0 },
                                { 0, 0, 0, 0 },
                                { 1, 1, 0, 0 },
                                { 1, 1, 0, 0 }
                        };

                }
            case 5:
                switch (rotation) {
                    case 0:
                    case 180:

                        return new int[][] {
                                { 0, 0, 0, 0 },
                                { 0, 0, 0, 0 },
                                { 0, 1, 1, 0 },
                                { 1, 1, 0, 0 }
                        };
                    case 90:
                    case 270:
                        return new int[][] {
                                { 0, 0, 0, 0 },
                                { 1, 0, 0, 0 },
                                { 1, 1, 0, 0 },
                                { 0, 1, 0, 0 }
                        };
                }
            case 6:
                switch (rotation) {
                    case 0:
                        return new int[][] {
                                { 0, 0, 0, 0 },
                                { 0, 0, 0, 0 },
                                { 0, 1, 0, 0 },
                                { 1, 1, 1, 0 }
                        };
                    case 90:
                        return new int[][] {
                                { 0, 0, 0, 0 },
                                { 0, 1, 0, 0 },
                                { 1, 1, 0, 0 },
                                { 0, 1, 0, 0 }
                        };
                    case 180:
                        return new int[][] {
                                { 0, 0, 0, 0 },
                                { 0, 0, 0, 0 },
                                { 1, 1, 1, 0 },
                                { 0, 1, 0, 0 }
                        };
                    case 270:
                        return new int[][] {
                                { 0, 0, 0, 0 },
                                { 1, 0, 0, 0 },
                                { 1, 1, 0, 0 },
                                { 1, 0, 0, 0 }
                        };
                }
            case 7:
                switch (rotation) {
                    case 0:
                    case 180:
                        return new int[][] {
                                { 0, 0, 0, 0 },
                                { 0, 0, 0, 0 },
                                { 1, 1, 0, 0 },
                                { 0, 1, 1, 0 }
                        };
                    case 90:
                    case 270:
                        return new int[][] {
                                { 0, 0, 0, 0 },
                                { 0, 1, 0, 0 },
                                { 1, 1, 0, 0 },
                                { 1, 0, 0, 0 }
                        };
                }
        }
        return new int[][] {
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
        };
    }
}