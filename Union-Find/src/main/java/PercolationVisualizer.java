import java.awt.Font;

public class PercolationVisualizer {

    // Prevent instantiation
    private PercolationVisualizer() {
    }

    // delay in milliseconds (controls animation speed)
    private static final int DELAY = 100;

    // draw N-by-N percolation system
    public static void draw(Percolation perc, int n) {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setXscale(0, n);
        StdDraw.setYscale(0, n);
        StdDraw.filledSquare(n / 2.0, n / 2.0, n / 2.0);

        // draw N-by-N grid
        int opened = 0;
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                if (perc.isFull(row, col)) {
                    StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                    opened++;
                } else if (perc.isOpen(row, col)) {
                    StdDraw.setPenColor(StdDraw.WHITE);
                    opened++;
                } else {
                    StdDraw.setPenColor(StdDraw.BLACK);
                }
                StdDraw.filledSquare(col - 0.5, n - row + 0.5, 0.45);
            }
        }

        // write status text
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(.25 * n, -n * .025, opened + " open sites");
        if (perc.percolates()) {
            StdDraw.text(.75 * n, -n * .025, "percolates");
        } else {
            StdDraw.text(.75 * n, -n * .025, "does not percolate");
        }

    }

    public static void main(String[] args) {
        // input file
        In in = new In(args[0]);
        // N-by-N percolation system
        int n = in.readInt();

        // turn on animation mode
        StdDraw.show(0);

        // repeatedly read in sites to open and draw resulting system
        Percolation perc = new Percolation(n);
        draw(perc, n);
        StdDraw.show(DELAY);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
            draw(perc, n);
            StdDraw.show(DELAY);
        }
    }
}
