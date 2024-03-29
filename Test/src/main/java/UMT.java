import java.util.*;


public class UMT {

    public boolean arraysSameAVG(int[] A) {
        int size = A.length;
        int sum = Arrays.stream(A).sum();
        //verificam daca vectorul are un singur element
        if (size <= 1)
            return false;

        //verificam daca sum elementelor vectorului este 0
        if (sum == 0)
            return true;

        //construim o matrice in care numarul coloanei reprezinta suma unui subsir din sirul initial
        //iar numarul liniei reprezinta din cate elemente e formata linia respectiva
        //matricea contine sumele tuturor subsirurilor
        //ex fr[3][10]= true reprezinta ca 10 este suma unui subsir format din 3 elemente
        boolean[][] fr = new boolean[size + 1][sum + 1];
        boolean[][] aux = new boolean[size + 1][sum + 1];

        aux[1][A[0]] = true;
        fr[1][A[0]] = true;
        //vom parcurge toate elementele din sir dupa cum urmeaza
        //vom lua fiecare element pe rand si il vom adauga la toate sumele din matrice
        for (int m = 1; m < size; m++) {
            fr[1][A[m]] = true;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < sum; j++) {
                    if (aux[i][j]) {
                        fr[i + 1][j + A[m]] = true;
                    }
                }

            }
            aux = copyFr(fr, sum, size);
        }


        fr[size][sum] = false;
        for (int i = sum; i > 0; i--) {
            for (int j = size; j > 0; j--) {
                if (fr[j][i] && ((float) i / (float) j) * size == sum) {
                    return true;
                }

            }
        }
        return false;
    }

    public boolean[][] copyFr(boolean[][] aux, int sum, int size) {
        boolean[][] res = new boolean[size + 1][sum + 1];

        for (int i = 0; i < size + 1; i++) {
            if (sum + 1 >= 0) System.arraycopy(aux[i], 0, res[i], 0, sum + 1);
        }

        return res;
    }


    public static void main(String[] args) {
        //int[] A = {1,2,3,4};
        int[] A = {4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 5};
        UMT umt = new UMT();
        System.out.println(umt.arraysSameAVG(A));

    }
}
