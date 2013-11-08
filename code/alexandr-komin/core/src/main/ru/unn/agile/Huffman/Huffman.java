package ru.unn.agile.Huffman;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Huffman {

    private static class Node implements Comparable<Node>  {
        public  char symbol;
        public int freq;
        public Node leftChild, rightChild;

        Node(char symbol, int freq, Node leftChild, Node rightChild){
            if((leftChild==null&& rightChild != null)||(leftChild != null&& rightChild == null)){
                throw new RuntimeException("Node can't have one child");
            }
            this.symbol = symbol;
            this.freq=freq;
            this.leftChild =leftChild;
            this.rightChild = rightChild;
        }

        public boolean isLeaf() {
            return (leftChild == null && rightChild == null);
        }

        public int compareTo(Node node) {
            return this.freq - node.freq;
        }
    }

    private static Node rootTree;
    private static int sizeArray = 65536;

    public static String compress(String inputText){

        if(inputText.isEmpty()) {
            throw new RuntimeException("Empty string are not allowed for compress");
        }

        char[] inputTextChars = inputText.toCharArray();
        int[] freqSymbols = new int[sizeArray];

        formArrFreqSymbols(freqSymbols, inputTextChars);

        rootTree = formTree(freqSymbols);

        String[] arrCodes = new String[sizeArray];
        formCodesOfSymbols(arrCodes, rootTree, "");

        return formOutputTextCompress(arrCodes, inputTextChars);
    }

    public static String expand(String inputText){

        if(inputText.isEmpty()){
            throw new RuntimeException("Empty string are not allowed for expand");
        }
        char[] inputTextChars = inputText.toCharArray();

        checkString(inputTextChars);

        return formOutputTextExpand(inputTextChars);
    }

    private static String formOutputTextCompress(String[] arrCodes, char[] inputChars){
         String outputText="";
         for (int i = 0; i < inputChars.length; i++) {
             outputText+=arrCodes[inputChars[i]];
         }
         return outputText;
     }

    private static String formOutputTextExpand(char[] inputChars) {
        String outputText="";
        for (int i = 0; i < inputChars.length; i++) {
            Node node = rootTree;
            while (!node.isLeaf()) {
                if (inputChars[i]=='1') node = node.rightChild;
                else                        node = node.leftChild;
                i++;
            }
            outputText+=node.symbol;
            i--;
        }
        return outputText;
    }

    private static void formArrFreqSymbols(int[] freqSymbols, char[] inputChars){
        for (int i = 0; i < inputChars.length; i++)
            freqSymbols[(int)inputChars[i]]++;
    }

    private static Node formTree(int[] freqSymbols) {

        Comparator<Node> comparator = new Comparator<Node>() {
            @Override
            public int compare(Node agr1, Node arg2) {
                if( agr1.freq < arg2.freq ){
                    return -1;
                }
                if( agr1.freq > arg2.freq ){
                    return 1;
                }
                return 0;
            }
        };


        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>(freqSymbols.length,comparator);
        for (int i = 0; i < sizeArray; i++)
            if (freqSymbols[i] > 0)
                priorityQueue.add(new Node((char)i, freqSymbols[i], null, null));

        while (priorityQueue.size() > 1) {
            Node leftChild  = priorityQueue.poll();
            Node rightChild = priorityQueue.poll();
            Node parent = new Node('\0', leftChild.freq + rightChild.freq, leftChild, rightChild);
            priorityQueue.add(parent);
        }
        return priorityQueue.poll();
    }

    private static void formCodesOfSymbols(String[] arrCodes, Node node, String code) {
        if (!node.isLeaf()) {
            formCodesOfSymbols(arrCodes, node.leftChild, code + '0');
            formCodesOfSymbols(arrCodes, node.rightChild, code + '1');
        }
        else {
            arrCodes[node.symbol] = code;
        }
    }

    private static void checkString(char[] inputTextChars){
        for (int i=0; i<inputTextChars.length; i++){
            if(inputTextChars[i]!='0'&&inputTextChars[i]!='1')
                throw new RuntimeException("Input string for expand contains incorrect symbol");
        }
    }
}
