import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 1406번
    // 연결 리스트
    // 현재 노드를 기준으로 앞뒤 방향으로 자유롭게 이동해야 한다.
    // 삭제 매커니즘이 연결 리스트의 것과 같다.
    // => 연결 리스트 중에서도 이중 연결 리스트임을 알 수 있다.
    // 여기서 가장 먼저 로직이 시작할 때 커서는 tail이다.

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split("");

        DoublyLinkedList list = new DoublyLinkedList();
        for (String s : input) {
            list.addTail(new Node(s));
        }

        int M = Integer.parseInt(br.readLine());
        Node cursor = list.getTailNode();

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if (st.countTokens() == 0) {
                if (command.equals("L")) {
                    if (cursor != null)
                        cursor = cursor.getPrev();
                } else if (command.equals("D")) {
                    if (cursor == null) {
                        cursor = list.getHeadNode();
                    } else if (cursor.getNext() != null) {
                        cursor = cursor.getNext();
                    }
                } else if (command.equals("B")) {
                    if (cursor != null) {
                        Node temp = cursor.getPrev();
                        list.deleteNode(cursor);
                        cursor = temp;
                    }
                }
            } else {
                String inputCharacter = st.nextToken();
                Node createdNode = new Node(inputCharacter);
                if (cursor == null) {
                    list.addHead(createdNode);
                } else {
                    list.addNode(createdNode, cursor);
                }
                cursor = createdNode;
            }
        }

        bw.write(printList(list));
        br.close();
        bw.flush();
        bw.close();
    }

    public static String printList(DoublyLinkedList list) {

        StringBuilder sb = new StringBuilder();
        Node currentNode = list.getHeadNode();

        while (currentNode.getNext() != null) {
            sb.append(currentNode.getData());
            currentNode = currentNode.getNext();
        }
        sb.append(list.getTailNode().getData());

        return sb.toString();
    }

    static class DoublyLinkedList {
        Node head;
        Node tail;

        public Node getHeadNode() {
            return head;
        }

        public Node getTailNode() {
            return tail;
        }

        public boolean addNode(Node node, Node cursor) {
            Node nextNode = cursor.getNext();
            node.setNext(cursor.getNext());
            node.setPrev(cursor);
            cursor.setNext(node);

            if (nextNode != null) {
                nextNode.setPrev(node);
            } else {
                this.tail = node;
            }

            return true;
        }

        public boolean deleteNode(Node node) {
            Node prevNode = node.getPrev();
            Node nextNode = node.getNext();
            if (prevNode != null) {
                prevNode.setNext(nextNode);
            } else {
                this.head = nextNode;
            }

            if (nextNode != null) {
                nextNode.setPrev(prevNode);
            } else {
                this.tail = prevNode;
            }

            node.setNext(null);
            node.setPrev(null);
            return true;
        }

        public boolean addHead(Node node) {
            if (head == null) {
                head = node;
                tail = node;
                return true;
            }

            node.setNext(head);
            head.setPrev(node);
            this.head = node;
            return true;
        }

        public boolean deleteHead() {
            if (head == null) {
                return true;
            }

            this.head = head.getNext();

            if (this.head != null) {
                this.head.setPrev(null);
            } else {
                this.tail = null;
            }

            return true;
        }

        public boolean addTail(Node node) {
            if (tail == null) {
                head = node;
                tail = node;
                return true;
            }

            node.setPrev(tail);
            tail.setNext(node);
            this.tail = node;
            return true;
        }

        public boolean deleteTail() {
            if (tail == null) {
                return true;
            }

            this.tail = tail.getPrev();

            if (this.tail != null) {
                this.tail.setNext(null);
            } else {
                this.head = null;
            }
            return true;
        }
    }

    static class Node {
        Node prev, next;
        String data;

        Node(String data) {
            this.data = data;
        }

        public Node getPrev() {
            return prev;
        }

        public Node getNext() {
            return next;
        }

        public String getData() {
            return data;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
