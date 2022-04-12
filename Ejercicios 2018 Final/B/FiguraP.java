class FiguraP {
    int[][] forma;
    int columna;

    FiguraP(int id, int rotacion, int columna) {
        this.forma = getMatrix(id, rotacion);
        this.columna = columna;
    }

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