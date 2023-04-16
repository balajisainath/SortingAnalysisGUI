
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


public class project2 {
}

//class SortingAlgorithmsGUI extends JFrame {
//
//    private JButton generateBtn;
//    private JButton playBtn;
//    private JPanel graphPanel;
//    private JButton playBtn1;
//    private JButton playBtn2;
//
//    public SortingAlgorithmsGUI() {
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setTitle("Sorting Algorithms");
//
//        generateBtn = new JButton("Generate Random Array");
//        playBtn = new JButton("Bar");
//        playBtn1 = new JButton("Pie");
//        playBtn2 = new JButton("line");// create new button
//        graphPanel = new JPanel();
//
//        String[] algorithms = {"Bubble Sort", "Selection Sort","Heap Sort", "Insertion Sort", "Merge Sort", "Quick Sort"};
//        JCheckBox[] algorithmCheckboxes = new JCheckBox[algorithms.length];
//        JPanel algorithmCheckboxPanel = new JPanel();
//        algorithmCheckboxPanel.setLayout(new GridLayout(algorithms.length, 1));
//        for (int i = 0; i < algorithms.length; i++) {
//            algorithmCheckboxes[i] = new JCheckBox(algorithms[i]);
//            algorithmCheckboxPanel.add(algorithmCheckboxes[i]);
//        }
//
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.add(generateBtn);
//        buttonPanel.add(playBtn);
//        buttonPanel.add(playBtn1);
//        buttonPanel.add(playBtn2);
//
//        JPanel leftPanel = new JPanel();
//        leftPanel.setLayout(new BorderLayout());
//        leftPanel.add(algorithmCheckboxPanel, BorderLayout.CENTER);
//
//        add(leftPanel, BorderLayout.WEST);
//        add(buttonPanel, BorderLayout.NORTH);
//        add(graphPanel, BorderLayout.CENTER);
//
//        setSize(1200, 500);
//        setVisible(true);
//
//        generateBtn.addActionListener(new GenerateButtonListener());
//        playBtn.addActionListener(new PlayButtonListener(graphPanel,algorithmCheckboxes));
//        playBtn1.addActionListener(new PlayButton1Listener(graphPanel,algorithmCheckboxes));
//        playBtn2.addActionListener(new PlayButton2Listener(graphPanel,algorithmCheckboxes));
//    }
//}
class SortingAlgorithmsGUI extends JFrame {

    private JButton generateBtn, playBtn, playBtn1, playBtn2;
    private JPanel graphPanel;

    public SortingAlgorithmsGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sorting Algorithms");

        generateBtn = new JButton("Generate Random Array");
        playBtn = new JButton("Bar");
        playBtn1 = new JButton("Pie");
        playBtn2 = new JButton("Line");
        graphPanel = new JPanel();

        String[] algorithms = {"Bubble Sort", "Selection Sort", "Heap Sort", "Insertion Sort", "Merge Sort", "Quick Sort"};
        JCheckBox[] algorithmCheckboxes = new JCheckBox[algorithms.length];
        JPanel algorithmCheckboxPanel = new JPanel();
        algorithmCheckboxPanel.setLayout(new GridLayout(algorithms.length + 1, 1)); // Add one extra row for "Select All" checkbox
        JCheckBox selectAllCheckbox = new JCheckBox("Select All");
        algorithmCheckboxPanel.add(selectAllCheckbox);
        selectAllCheckbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (JCheckBox checkbox : algorithmCheckboxes) {
                    checkbox.setSelected(selectAllCheckbox.isSelected());
                }
            }
        });
        for (int i = 0; i < algorithms.length; i++) {
            algorithmCheckboxes[i] = new JCheckBox(algorithms[i]);
            algorithmCheckboxPanel.add(algorithmCheckboxes[i]);
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(generateBtn);
        buttonPanel.add(playBtn);
        buttonPanel.add(playBtn1);
        buttonPanel.add(playBtn2);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(algorithmCheckboxPanel, BorderLayout.CENTER);

        add(leftPanel, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.NORTH);
        add(graphPanel, BorderLayout.CENTER);

        setSize(1200, 500);
        setVisible(true);

        generateBtn.addActionListener(new GenerateButtonListener());
        playBtn.addActionListener(new PlayButtonListener(graphPanel, algorithmCheckboxes));
        playBtn1.addActionListener(new PlayButton1Listener(graphPanel, algorithmCheckboxes));
        playBtn2.addActionListener(new PlayButton2Listener(graphPanel, algorithmCheckboxes));
    }

}


class RandomArrayGenerator {
    private static int[] arr;
    public static int[] generateArray(int size) {
        arr = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(size * 10);
        }
        return arr;
    }
    public static int[] getArray() {
        return arr;
    }
}

    class BubbleSort {

        public static void sort(int[] arr) {
            int n = arr.length;
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (arr[j] > arr[j + 1]) {
                        // swap arr[j] and arr[j+1]
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        }
    }

    class InsertionSort {

        public static void sort(int[] arr) {
            int n = arr.length;
            for (int i = 1; i < n; i++) {
                int key = arr[i];
                int j = i - 1;

                while (j >= 0 && arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j = j - 1;
                }
                arr[j + 1] = key;
            }
        }
    }

    class HeapSort {

        public static void sort(int[] arr) {
            int n = arr.length;

            for (int i = n / 2 - 1; i >= 0; i--) {
                heapify(arr, n, i);
            }

            for (int i = n - 1; i > 0; i--) {
                int temp = arr[0];
                arr[0] = arr[i];
                arr[i] = temp;

                heapify(arr, i, 0);
            }
        }

        private static void heapify(int[] arr, int n, int i) {
            int largest = i;
            int l = 2 * i + 1;
            int r = 2 * i + 2;

            if (l < n && arr[l] > arr[largest]) {
                largest = l;
            }

            if (r < n && arr[r] > arr[largest]) {
                largest = r;
            }

            if (largest != i) {
                int temp = arr[i];
                arr[i] = arr[largest];
                arr[largest] = temp;

                heapify(arr, n, largest);
            }
        }
    }

    // merge sort
    class MergeSort {

        private static final int INSERTION_THRESHOLD = 10;

        public static void sort(int[] arr) {
            int[] aux = new int[arr.length];
            sort(arr, aux, 0, arr.length - 1);
        }

        private static void sort(int[] arr, int[] aux, int left, int right) {
            if (left >= right) {
                return;
            }

            if (right - left + 1 <= INSERTION_THRESHOLD) {
                insertionSort(arr, left, right);
                return;
            }

            int mid = left + (right - left) / 2;
            sort(arr, aux, left, mid);
            sort(arr, aux, mid + 1, right);
            merge(arr, aux, left, mid, right);
        }

        private static void merge(int[] arr, int[] aux, int left, int mid, int right) {
            if (arr[mid] <= arr[mid + 1]) {
                return;
            }

            System.arraycopy(arr, left, aux, left, right - left + 1);

            int i = left, j = mid + 1;
            for (int k = left; k <= right; k++) {
                if (i > mid) {
                    arr[k] = aux[j++];
                } else if (j > right) {
                    arr[k] = aux[i++];
                } else if (aux[i] <= aux[j]) {
                    arr[k] = aux[i++];
                } else {
                    arr[k] = aux[j++];
                }
            }
        }

        private static void insertionSort(int[] arr, int left, int right) {
            for (int i = left + 1; i <= right; i++) {
                int key = arr[i];
                int j = i - 1;
                while (j >= left && arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                }
                arr[j + 1] = key;
            }
        }
    }

    //qucik sort
    class QuickSort {

        public static void sort(int[] arr) {
            sort(arr, 0, arr.length - 1);
        }

        private static void sort(int[] arr, int low, int high) {
            if (low < high) {
                int pivotIndex = partition(arr, low, high);
                sort(arr, low, pivotIndex - 1);
                sort(arr, pivotIndex + 1, high);
            }
        }

        private static int partition(int[] arr, int low, int high) {
            int pivot = arr[high];
            int i = low - 1;
            for (int j = low; j < high; j++) {
                if (arr[j] < pivot) {
                    i++;
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            int temp = arr[i + 1];
            arr[i + 1] = arr[high];
            arr[high] = temp;
            return i + 1;
        }
    }


    class SelectionSort {

        public static void sort(int[] arr) {
            int n = arr.length;
            for (int i = 0; i < n - 1; i++) {
                int min_idx = i;
                for (int j = i + 1; j < n; j++) {
                    if (arr[j] < arr[min_idx]) {
                        min_idx = j;
                    }
                }
                // swap arr[i] and arr[min_idx]
                int temp = arr[min_idx];
                arr[min_idx] = arr[i];
                arr[i] = temp;
            }
        }
    }

    class TimSort {

        private static final int RUN = 32;

        public static void sort(int[] arr) {
            int n = arr.length;
            for (int i = 0; i < n; i += RUN) {
                insertionSort(arr, i, Math.min((i + RUN - 1), (n - 1)));
            }
            for (int size = RUN; size < n; size = 2 * size) {
                for (int left = 0; left < n; left += 2 * size) {
                    int mid = left + size - 1;
                    int right = Math.min((left + 2 * size - 1), (n - 1));
                    merge(arr, left, mid, right);
                }
            }
        }

        private static void insertionSort(int[] arr, int left, int right) {
            for (int i = left + 1; i <= right; i++) {
                int temp = arr[i];
                int j = i - 1;
                while (j >= left && arr[j] > temp) {
                    arr[j + 1] = arr[j];
                    j--;
                }
                arr[j + 1] = temp;
            }
        }

        private static void merge(int[] arr, int left, int mid, int right) {
            int len1 = mid - left + 1;
            int len2 = right - mid;
            int[] leftArr = new int[len1];
            int[] rightArr = new int[len2];
            for (int i = 0; i < len1; i++) {
                leftArr[i] = arr[left + i];
            }
            for (int i = 0; i < len2; i++) {
                rightArr[i] = arr[mid + 1 + i];
            }
            int i = 0, j = 0, k = left;
            while (i < len1 && j < len2) {
                if (leftArr[i] <= rightArr[j]) {
                    arr[k] = leftArr[i];
                    i++;
                } else {
                    arr[k] = rightArr[j];
                    j++;
                }
                k++;
            }
            while (i < len1) {
                arr[k] = leftArr[i];
                i++;
                k++;
            }
            while (j < len2) {
                arr[k] = rightArr[j];
                j++;
                k++;
            }
        }
    }

    // cycle sort
    class CycleSort {

        public static void sort(int[] arr) {
            int n = arr.length;

            for (int cycleStart = 0; cycleStart < n - 1; cycleStart++) {
                int item = arr[cycleStart];

                int pos = cycleStart;
                for (int i = cycleStart + 1; i < n; i++) {
                    if (arr[i] < item) {
                        pos++;
                    }
                }

                if (pos == cycleStart) {
                    continue;
                }

                while (item == arr[pos]) {
                    pos++;
                }

                if (pos != cycleStart) {
                    int temp = item;
                    item = arr[pos];
                    arr[pos] = temp;
                }

                while (pos != cycleStart) {
                    pos = cycleStart;
                    for (int i = cycleStart + 1; i < n; i++) {
                        if (arr[i] < item) {
                            pos++;
                        }
                    }

                    while (item == arr[pos]) {
                        pos++;
                    }

                    if (item != arr[pos]) {
                        int temp = item;
                        item = arr[pos];
                        arr[pos] = temp;
                    }
                }
            }
        }
    }

    class GenerateButtonListener implements ActionListener {

        @Override

//    public void actionPerformed(ActionEvent e) {
//        int[] arr = RandomArrayGenerator.generateArray(1000);
//        // JOptionPane.showMessageDialog(null, Arrays.toString(arr), "Random Array", JOptionPane.PLAIN_MESSAGE);
//    }
        public void actionPerformed(ActionEvent e) {
            String sizeStr = JOptionPane.showInputDialog(null, "Enter the size of the array:", "Array Size", JOptionPane.PLAIN_MESSAGE);
            if (sizeStr == null) {
                // User clicked Cancel or closed the dialog
                return;
            }
            int size = Integer.parseInt(sizeStr);
            int[] arr = RandomArrayGenerator.generateArray(size);

            JTextArea textArea = new JTextArea(20, 60);
            textArea.setText(Arrays.toString(arr));
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            JOptionPane.showMessageDialog(null, scrollPane, "Random Array", JOptionPane.PLAIN_MESSAGE);
        }

    }

class PlayButton2Listener implements ActionListener {

    private JPanel graphPanel;
    private JCheckBox[] algorithmCheckboxes;

    public PlayButton2Listener(JPanel graphPanel, JCheckBox[] algorithmCheckboxes) {
        this.graphPanel = graphPanel;
        this.algorithmCheckboxes = algorithmCheckboxes;
    }

    public void actionPerformed(ActionEvent e) {
        int[] arr1 = RandomArrayGenerator.getArray();
        int[] arr = arr1.clone();
        long startTime, endTime;

        // Create a line chart of sorting run time
        DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();

        for (int i = 0; i < algorithmCheckboxes.length; i++) {
            if (algorithmCheckboxes[i].isSelected()) {
                String algorithmName = algorithmCheckboxes[i].getText();

                // Add runtime of selected algorithm to the line dataset
                switch (algorithmName) {
                    case "Bubble Sort":
                        startTime = System.nanoTime();
                        BubbleSort.sort(arr);
                        endTime = System.nanoTime();
                        long bubbleSortTime = endTime - startTime;
                        lineDataset.setValue(bubbleSortTime, "Time", "Bubble Sort");
                        break;
                    case "Insertion Sort":

                        startTime = System.nanoTime();
                        InsertionSort.sort(arr);
                        endTime = System.nanoTime();
                        long insertionSortTime = endTime - startTime;
                        lineDataset.setValue(insertionSortTime, "Time", "Insertion Sort");
                        break;
                    case "Selection Sort":
                        startTime = System.nanoTime();
                        SelectionSort.sort(arr);
                        endTime = System.nanoTime();
                        long selectionSortTime = endTime - startTime;
                        lineDataset.setValue(selectionSortTime, "Time", "Selection Sort");
                        break;
                    case "Heap Sort":
                        startTime = System.nanoTime();
                        HeapSort.sort(arr);
                        endTime = System.nanoTime();
                        long heapSortTime = endTime - startTime;
                        lineDataset.setValue(heapSortTime, "Time", "Heap Sort");
                        break;
                    case "Merge Sort":
                        startTime = System.nanoTime();
                        MergeSort.sort(arr);
                        endTime = System.nanoTime();
                        long mergeSortTime = endTime - startTime;
                        lineDataset.setValue(mergeSortTime, "Time", "Merge Sort");
                        break;
                    case "Quick Sort":
                        startTime = System.nanoTime();
                        QuickSort.sort(arr);
                        endTime = System.nanoTime();
                        long quickSortTime = endTime - startTime;
                        lineDataset.setValue(quickSortTime, "Time", "Quick Sort");
                        break;
                }
            }
        }

        JFreeChart lineChart = ChartFactory.createLineChart("Sorting Algorithms Run Time In ns", "Algorithm", "Time (ns)", lineDataset, PlotOrientation.VERTICAL, false, true, false);

        // Set color of lines
        CategoryPlot linePlot = lineChart.getCategoryPlot();
        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesPaint(1, Color.RED);
        renderer.setSeriesPaint(2, Color.GREEN);
        renderer.setSeriesPaint(3, Color.ORANGE);
        renderer.setSeriesPaint(4, Color.PINK);
        renderer.setSeriesPaint(5, Color.MAGENTA);
        linePlot.setRenderer(renderer);
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new Dimension(700, 400));
        graphPanel.removeAll();
        graphPanel.add(chartPanel);
        graphPanel.revalidate();
        graphPanel.repaint();
    }
}
class PlayButton1Listener implements ActionListener {

    private JPanel graphPanel;
    private JCheckBox[] algorithmCheckboxes;

    public PlayButton1Listener(JPanel graphPanel, JCheckBox[] algorithmCheckboxes) {
        this.graphPanel = graphPanel;
        this.algorithmCheckboxes = algorithmCheckboxes;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get selected algorithms from checkboxes
        java.util.List<String> selectedAlgorithms = new ArrayList<String>();
        for (JCheckBox checkbox : algorithmCheckboxes) {
            if (checkbox.isSelected()) {
                selectedAlgorithms.add(checkbox.getText());
            }
        }

        int[] arr1 = RandomArrayGenerator.getArray();
        int[] arr=arr1.clone();
        long startTime, endTime;

        // Sort selected algorithms and calculate run time
        Map<String, Long> algorithmTimes = new HashMap<>();
        for (String algorithm : selectedAlgorithms) {
            switch (algorithm) {
                case ("Bubble Sort"):
                    startTime = System.nanoTime();
                    BubbleSort.sort(arr);
                    endTime = System.nanoTime();
                    algorithmTimes.put(algorithm, endTime - startTime);
                    break;
                case "Insertion Sort":
                    startTime = System.nanoTime();
                    InsertionSort.sort(arr);
                    endTime = System.nanoTime();
                    algorithmTimes.put(algorithm, endTime - startTime);
                    break;
                case "Selection Sort":
                    startTime = System.nanoTime();
                    SelectionSort.sort(arr);
                    endTime = System.nanoTime();
                    algorithmTimes.put(algorithm, endTime - startTime);
                    break;
                case "Heap Sort":
                    startTime = System.nanoTime();
                    HeapSort.sort(arr);
                    endTime = System.nanoTime();
                    algorithmTimes.put(algorithm, endTime - startTime);
                    break;
                case "Merge Sort":
                    startTime = System.nanoTime();
                    MergeSort.sort(arr);
                    endTime = System.nanoTime();
                    algorithmTimes.put(algorithm, endTime - startTime);
                    break;
                case "Quick Sort":
                    startTime = System.nanoTime();
                    QuickSort.sort(arr);
                    endTime = System.nanoTime();
                    algorithmTimes.put(algorithm, endTime - startTime);
                    break;
            }
        }

        // Create a dataset for pie chart
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (String algorithm : selectedAlgorithms) {
            dataset.setValue(algorithm, algorithmTimes.get(algorithm));
        }

        // Create a pie chart
        JFreeChart chart = ChartFactory.createPieChart("Sorting Algorithms Run Time in ns", dataset, true, true, false);

        // Set color of pie chart sections
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint(1, Color.blue);
        plot.setSectionPaint(2, Color.green);
        plot.setSectionPaint(3, Color.red);
        plot.setSectionPaint(4, Color.yellow);
        plot.setSectionPaint(5, Color.pink);
        plot.setSectionPaint(6, Color.ORANGE);

        // Add chart to graph panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 400));
        graphPanel.removeAll();
        graphPanel.add(chartPanel);
        graphPanel.revalidate();
        graphPanel.repaint();
    }
}



class PlayButtonListener implements ActionListener {

    private JPanel graphPanel;
    private JCheckBox[] algorithmCheckboxes;

    public PlayButtonListener(JPanel graphPanel, JCheckBox[] algorithmCheckboxes) {
        this.graphPanel = graphPanel;
        this.algorithmCheckboxes = algorithmCheckboxes;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int[] arr1 = RandomArrayGenerator.getArray();
        int[]arr=arr1.clone();
        long startTime, endTime;

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < algorithmCheckboxes.length; i++) {
            if (algorithmCheckboxes[i].isSelected()) {
                switch (algorithmCheckboxes[i].getText()) {
                    case "Bubble Sort":
                        startTime = System.nanoTime();
                        BubbleSort.sort(arr);
                        endTime = System.nanoTime();
                        dataset.setValue(endTime - startTime, "Time", "Bubble Sort");
                        break;
                    case "Selection Sort":
                        startTime = System.nanoTime();
                        SelectionSort.sort(arr);
                        endTime = System.nanoTime();
                        dataset.setValue(endTime - startTime, "Time", "Selection Sort");
                        break;
                    case "Insertion Sort":
                        startTime = System.nanoTime();
                        InsertionSort.sort(arr);
                        endTime = System.nanoTime();
                        dataset.setValue(endTime - startTime, "Time", "Insertion Sort");
                        break;
                    case "Merge Sort":
                        startTime = System.nanoTime();
                        MergeSort.sort(arr);
                        endTime = System.nanoTime();
                        dataset.setValue(endTime - startTime, "Time", "Merge Sort");
                        break;
                    case "Quick Sort":
                        startTime = System.nanoTime();
                        QuickSort.sort(arr);
                        endTime = System.nanoTime();
                        dataset.setValue(endTime - startTime, "Time", "Quick Sort");
                        break;
                    case "Heap Sort":
                        startTime=System.nanoTime();
                        HeapSort.sort(arr);
                        endTime=System.nanoTime();
                        dataset.setValue(endTime - startTime, "Time","Heap Sort");
                    default:
                        break;
                }
            }
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Sorting Algorithm Performance", // chart title
                "Algorithm", // domain axis label
                "Time (nanoseconds)", // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips
                false // urls
        );

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.BLACK);

        ChartPanel chartPanel = new ChartPanel(chart);
        graphPanel.removeAll();
        graphPanel.add(chartPanel);
        graphPanel.revalidate();
        graphPanel.repaint();
    }
}

