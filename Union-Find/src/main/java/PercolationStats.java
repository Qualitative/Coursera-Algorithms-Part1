public class PercolationStats {

    private final int T;
    private double [] fractions;

    public PercolationStats(int N, int T) {

        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException("N and T values must be positive");

        this.T = T;
        this.fractions = new double[T];

        for (int i = 0; i < T; i++) {

            Percolation perc = new Percolation(N);
            int sitesCount = 0;

            while (!perc.percolates()) {
                int row = StdRandom.uniform(1, N + 1);
                int col = StdRandom.uniform(1, N + 1);
                if (perc.isOpen(row, col))
                    continue;
                perc.open(row, col);
                sitesCount++;
            }

            fractions[i] = (double) sitesCount / (N * N);
        }

    }

    public double mean() {
        return StdStats.mean(fractions);
    }

    public double stddev() {
        if (T == 1)
            return Double.NaN;
        return StdStats.stddev(fractions);
    }

    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(200, 100);
        System.out.println(percolationStats.mean());
        System.out.println(percolationStats.stddev());
        System.out.println(percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi());
    }
}
