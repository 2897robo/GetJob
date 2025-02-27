import java.util.*;
import java.io.*;

public class Main {
    static class Node {
        int stationNumber;
        Node prev, next;

        public Node(int s) {
            this.stationNumber = s;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<Integer, Node> map = new HashMap<>();

        st = new StringTokenizer(br.readLine(), " ");
        Node head = new Node(Integer.parseInt(st.nextToken()));
        map.put(head.stationNumber, head);
        Node prevNode = head;

        for(int i=1; i<n; i++) {
            Node current = new Node(Integer.parseInt(st.nextToken()));
            map.put(current.stationNumber, current);

            prevNode.next = current;
            current.prev = prevNode;
            prevNode = current;
        }

        prevNode.next = head;
        head.prev = prevNode;

        while(m-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            String cmd = st.nextToken();
            int i = Integer.parseInt(st.nextToken());
            Node current = map.get(i);

            switch(cmd) {
                case "BN" : {
                    int j = Integer.parseInt(st.nextToken());
                    sb.append(current.next.stationNumber).append("\n");

                    Node newNode = new Node(j);
                    map.put(j, newNode);

                    newNode.next = current.next;
                    newNode.prev = current;
                    current.next.prev = newNode;
                    current.next = newNode;
                    break;
                }
                case "BP" : {
                    int j = Integer.parseInt(st.nextToken());
                    sb.append(current.prev.stationNumber).append("\n");

                    Node newNode = new Node(j);
                    map.put(j, newNode);

                    newNode.prev = current.prev;
                    newNode.next = current;
                    current.prev.next = newNode;
                    current.prev = newNode;
                    break;
                }
                case "CN" : {
                    if(map.size() > 2) {
                        Node toRemove = current.next;
                        sb.append(toRemove.stationNumber).append("\n");

                        current.next = toRemove.next;
                        toRemove.next.prev = current;

                        map.remove(toRemove.stationNumber);
                    }
                    break;
                }
                case "CP" : {
                    if(map.size() > 2) {
                        Node toRemove = current.prev;
                        sb.append(toRemove.stationNumber).append("\n");

                        current.prev = toRemove.prev;
                        toRemove.prev.next = current;

                        map.remove(toRemove.stationNumber);
                    }
                    break;
                }
            }
        }

        bw.write(sb.toString());
        bw.flush();
        br.close(); bw.close();

    }
}
