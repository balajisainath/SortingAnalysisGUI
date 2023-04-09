
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;


public class project2 {
}

class SortingAlgorithmsGUI extends JFrame {

    private JButton generateBtn;
    private JButton playBtn;
    private JPanel graphPanel;
    private JButton playBtn1;

    public SortingAlgorithmsGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sorting Algorithms");

        generateBtn = new JButton("Generate Random Array");
        playBtn = new JButton("Bar");
        playBtn1 = new JButton("Pie"); // create new button
        graphPanel = new JPanel();

        // Add components to the JFrame
        JPanel buttonPanel = new JPanel(); // create a new panel to hold the buttons
        buttonPanel.add(generateBtn);
        buttonPanel.add(playBtn);
        buttonPanel.add(playBtn1); // add the new button to the panel
        add(buttonPanel, BorderLayout.NORTH);
        add(graphPanel, BorderLayout.CENTER);

        setSize(1200, 500);
        setVisible(true);

        generateBtn.addActionListener(new GenerateButtonListener());
        playBtn.addActionListener(new PlayButtonListener(graphPanel));
        playBtn1.addActionListener(new PlayButton1Listener(graphPanel));
    }

}
class RandomArrayGenerator {

    public static int[] generateArray(int size) {
        int[] arr = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(size * 10);
        }
        return arr;
    }
}
class BubbleSort {

    public static void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (arr[j] > arr[j+1]) {
                    // swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
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
                arr[j+1] = arr[j];
                j = j - 1;
            }
            arr[j+1] = key;
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
    }private static void heapify(int[] arr, int n, int i) {
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
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        return i+1;
    }
}


class SelectionSort {

    public static void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            int min_idx = i;
            for (int j = i+1; j < n; j++) {
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
        int[] arr = RandomArrayGenerator.generateArray(1000);
        JTextArea textArea = new JTextArea(20, 60);
        textArea.setText(Arrays.toString(arr));
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JOptionPane.showMessageDialog(null, scrollPane, "Random Array", JOptionPane.PLAIN_MESSAGE);
    }

}
class PlayButton1Listener implements ActionListener {

    private JPanel graphPanel;

    public PlayButton1Listener(JPanel graphPanel) {
        this.graphPanel = graphPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int[] arr = RandomArrayGenerator.generateArray(1000);
        long startTime, endTime;

        // Bubble Sort
        startTime = System.nanoTime();
        BubbleSort.sort(arr);
        endTime = System.nanoTime();
        long bubbleSortTime = endTime - startTime;

        // Insertion Sort
        arr = RandomArrayGenerator.generateArray(1000);
        startTime = System.nanoTime();
        InsertionSort.sort(arr);
        endTime = System.nanoTime();
        long insertionSortTime = endTime - startTime;

        // Selection Sort
        arr = RandomArrayGenerator.generateArray(1000);
        startTime = System.nanoTime();
        SelectionSort.sort(arr);
        endTime = System.nanoTime();
        long selectionSortTime = endTime - startTime;

        //heap sort
        arr= RandomArrayGenerator.generateArray(1000);
        startTime=System.nanoTime();
        HeapSort.sort(arr);
        endTime=System.nanoTime();
        long heapSortTime=endTime-startTime;

        // merge sort
        arr=RandomArrayGenerator.generateArray(1000);
        startTime=System.nanoTime();
        MergeSort.sort(arr);
        endTime=System.nanoTime();
        long mergeSortTime=endTime-startTime;

        //qucksort
        arr=RandomArrayGenerator.generateArray(1000);
        startTime=System.nanoTime();
        QuickSort.sort(arr);
        endTime=System.nanoTime();
        long qucikSortTime=endTime-startTime;

        // Create a dataset for pie chart
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Bubble Sort", bubbleSortTime);
        dataset.setValue("Insertion Sort", insertionSortTime);
        dataset.setValue("Selection Sort", selectionSortTime);
        dataset.setValue("heapSort",heapSortTime);
        dataset.setValue("QuickSort",qucikSortTime);
        dataset.setValue("MergeSort",mergeSortTime);

        // Create a pie chart
        JFreeChart chart = ChartFactory.createPieChart("Sorting Algorithms Run Time in ns", dataset, true, true, false);

        // Set color of pie chart sections
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint(1, Color.blue);
        plot.setSectionPaint(2, Color.green);
        plot.setSectionPaint(3, Color.red);
        plot.setSectionPaint(4,Color.yellow);
        plot.setSectionPaint(5,Color.pink);
        plot.setSectionPaint(6,Color.ORANGE);

        // Add chart to graph panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 300));
        graphPanel.removeAll();
        graphPanel.add(chartPanel);
        graphPanel.revalidate();
        graphPanel.repaint();
    }


}



class PlayButtonListener implements ActionListener {

    private JPanel graphPanel;

    public PlayButtonListener(JPanel graphPanel) {
        this.graphPanel = graphPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int[] arr = RandomArrayGenerator.generateArray(1000);
        long startTime, endTime;

        // Bubble Sort
        startTime = System.nanoTime();
        BubbleSort.sort(arr);
        endTime = System.nanoTime();
        long bubbleSortTime = endTime - startTime;

        // Insertion Sort
        arr = RandomArrayGenerator.generateArray(1000);
        startTime = System.nanoTime();
        InsertionSort.sort(arr);
        endTime = System.nanoTime();
        long insertionSortTime = endTime - startTime;

        // Selection Sort
        arr = RandomArrayGenerator.generateArray(1000);
        startTime = System.nanoTime();
        SelectionSort.sort(arr);
        endTime = System.nanoTime();
        long selectionSortTime = endTime - startTime;

        //heap sort
        arr= RandomArrayGenerator.generateArray(1000);
        startTime=System.nanoTime();
        HeapSort.sort(arr);
        endTime=System.nanoTime();
        long heapSortTime=endTime-startTime;

        // merge sort
        arr=RandomArrayGenerator.generateArray(1000);
        startTime=System.nanoTime();
        MergeSort.sort(arr);
        endTime=System.nanoTime();
        long mergeSortTime=endTime-startTime;

        //qucksort
        arr=RandomArrayGenerator.generateArray(1000);
        startTime=System.nanoTime();
        QuickSort.sort(arr);
        endTime=System.nanoTime();
        long qucikSortTime=endTime-startTime;

        //timsort
        arr=RandomArrayGenerator.generateArray(1000);
        startTime=System.nanoTime();
        TimSort.sort(arr);
        endTime=System.nanoTime();
        long timSorttime=endTime-startTime;

        //cycle sort
        arr=RandomArrayGenerator.generateArray(1000);
        startTime=System.nanoTime();
        CycleSort.sort(arr);
        endTime=System.nanoTime();
        long Cyclesorttime=endTime-startTime;


        // Create a bar chart of sorting run time
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(bubbleSortTime, "Time", "Bubble Sort");
        dataset.setValue(insertionSortTime, "Time", "Insertion Sort");
        dataset.setValue(selectionSortTime, "Time", "Selection Sort");
        dataset.setValue(heapSortTime,"Time","Heap Sort");
        dataset.setValue(qucikSortTime,"Time","Quick");
        dataset.setValue(mergeSortTime,"Time","Merge Sort");
        // dataset.setValue(timSorttime,"Time","TimSort");
        //  dataset.setValue(Cyclesorttime,"Time","Cyclesort");
        JFreeChart chart = ChartFactory.createBarChart("Sorting Algorithms Run Time In ns", "Algorithm", "Time (ns)", dataset, PlotOrientation.VERTICAL, false, true, false);

        // Set color of bars
        CategoryPlot plot = chart.getCategoryPlot();
        plot.getRenderer().setSeriesPaint(0, Color.blue);
        plot.getRenderer().setSeriesPaint(1, Color.green);
        plot.getRenderer().setSeriesPaint(2, Color.red);
        plot.getRenderer().setSeriesPaint(3,Color.green);

        // Add chart to graph panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 300));
        graphPanel.removeAll();
        graphPanel.add(chartPanel);
        graphPanel.revalidate();
        graphPanel.repaint();
    }



}
