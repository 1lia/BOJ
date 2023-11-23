import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        int N = readInt();
        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = readInt();
            }
        }
        int[] res = new Hungarian(arr).run();
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += arr[i][res[i]];
        }
        System.out.println(sum);
    }

     public static int readInt() throws Exception {
         int val = 0;
         int c = System.in.read();
         while (c <= ' ') {
             c = System.in.read();
         }
         boolean flag = (c == '-');
         if (flag) c = System.in.read();
         do {
             val = 10 * val + c - 48;
         } while ((c = System.in.read()) >= 48 && c <= 57);

         if (flag) return -val;
         return val;
     }
}

class Hungarian {
    private final int[][] matrix;
    private final int N;
    private final int[] labelWorker, labelJob;
    private final int[] slackWorker, slackJob;
    private final int[] matchJob, matchWorker;
    private final int[] parentWorker;
    private final boolean[] committed;

    public Hungarian(int[][] costMatrix) {
        N = costMatrix.length;
        matrix = new int[N][N];
        labelWorker = new int[N];
        labelJob = new int[N];
        slackWorker = new int[N];
        slackJob = new int[N];
        committed = new boolean[N];
        parentWorker = new int[N];
        matchJob = new int[N];
        matchWorker = new int[N];

        Arrays.fill(matchJob, -1);
        Arrays.fill(matchWorker, -1);

        for (int i = 0; i < N; i++)
            this.matrix[i] = Arrays.copyOf(costMatrix[i], N);
    }

    public int[] run() {
        reduce();
        computeInitial();
        greedyMatch();

        int i = findUnmatchWorker();
        while (i < N) {
            initializePhase(i);
            executePhase();
            i = findUnmatchWorker();
        }

        int[] result = Arrays.copyOf(matchJob, N);
        for (i = 0; i < result.length; i++)
            if (result[i] >= N)
                result[i] = -1;

        return result;
    }

    private void reduce() {
        for (int i = 0; i < N; i++) {
            int min = Integer.MAX_VALUE;

            for (int j = 0; j < N; j++)
                min = Math.min(matrix[i][j] , min);

            for (int j = 0; j < N; j++)
                matrix[i][j] -= min;
        }

        int[] min = new int[N];
        Arrays.fill(min, Integer.MAX_VALUE);

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                min[j] = Math.min(matrix[i][j] , min[j]);

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                matrix[i][j] -= min[j];
    }

    private void computeInitial() {
        Arrays.fill(labelJob, Integer.MAX_VALUE);

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (matrix[i][j] < labelJob[j])
                    labelJob[j] = matrix[i][j];
    }

    private void greedyMatch() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (matchJob[i] == -1 && matchWorker[j] == -1 && matrix[i][j] - labelWorker[i] - labelJob[j] == 0)
                    match(i, j);
    }

    private void executePhase() {
        while (true) {
            int minSlackWorker = -1, minSlackJob = -1;
            int minSlackValue = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                if (parentWorker[i] == -1) {
                    if (slackJob[i] < minSlackValue) {
                        minSlackValue = slackJob[i];
                        minSlackWorker = slackWorker[i];
                        minSlackJob = i;
                    }
                }
            }

            if (minSlackValue > 0)
                updateLabel(minSlackValue);

            parentWorker[minSlackJob] = minSlackWorker;
            if (matchWorker[minSlackJob] == -1) {
                int committedJob = minSlackJob;
                int parent = parentWorker[committedJob];

                while (true) {
                    int temp = matchJob[parent];
                    match(parent, committedJob);
                    committedJob = temp;

                    if (committedJob == -1)
                        break;

                    parent = parentWorker[committedJob];
                }

                return;
            } else {
                int worker = matchWorker[minSlackJob];
                committed[worker] = true;

                for (int i = 0; i < N; i++) {
                    if (parentWorker[i] == -1) {
                        int slack = matrix[worker][i] - labelWorker[worker] - labelJob[i];

                        if (slackJob[i] > slack) {
                            slackJob[i] = slack;
                            slackWorker[i] = worker;
                        }
                    }
                }
            }
        }
    }

    private int findUnmatchWorker() {
        int i;
        for (i = 0; i < N; i++)
            if (matchJob[i] == -1)
                break;

        return i;
    }


    private void initializePhase(int i) {
        Arrays.fill(committed, false);
        Arrays.fill(parentWorker, -1);
        committed[i] = true;

        for (int j = 0; j < N; j++) {
            slackJob[j] = matrix[i][j] - labelWorker[i] - labelJob[j];
            slackWorker[j] = i;
        }
    }

    private void match(int i, int j) {
        matchJob[i] = j;
        matchWorker[j] = i;
    }

    private void updateLabel(int slack) {
        for (int i = 0; i < N; i++)
            if (committed[i])
                labelWorker[i] += slack;

        for (int i = 0; i < N; i++) {
            if (parentWorker[i] != -1)
                labelJob[i] -= slack;
            else
                slackJob[i] -= slack;
        }
    }
}