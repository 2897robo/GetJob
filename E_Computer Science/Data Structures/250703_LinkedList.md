# 연결 리스트 (Linked List)

**연결 리스트**는 데이터를 **노드(Node)**의 형태로 저장하며, 각 노드가 다음 노드를 가리키는 방식으로 연결된 **선형 자료구조**이다. 배열과 달리 데이터의 추가/삭제가 유연하다는 장점이 있지만, 특정 위치에 있는 데이터에 바로 접근하기 어렵다는 단점이 있다. 마치 기차의 각 칸(노드)이 연결되어 있지만, 특정 칸으로 가려면 처음부터 순서대로 지나가야 하는 것과 같다.

-----

## 1\. 단일 연결 리스트 (Singly Linked List)

### 개념

**단일 연결 리스트**는 이름 그대로 **한 방향으로만 연결된 리스트**이다. 각 노드는 **데이터**와 **다음 노드를 가리키는 `next` 포인터**로 구성된다. 리스트의 가장 앞을 가리키는 **`head`** 포인터가 있으며, 마지막 노드의 `next` 포인터는 더 이상 가리킬 노드가 없으므로 `null`을 가리킨다.

데이터를 추가하거나 삭제할 때, 해당 위치의 노드와 그 앞뒤 노드의 `next` 포인터만 변경하면 되므로 배열처럼 데이터를 이동시킬 필요가 없어 효율적이다. 하지만 특정 위치의 데이터를 찾으려면 `head`부터 시작하여 순차적으로 탐색해야 한다.

### 구조 (Diagram)

```
[Head] -> [Node 1 (Data|Next)] -> [Node 2 (Data|Next)] -> ... -> [Node N (Data|Next)] -> null
```

### Java 코드 예시

#### 노드 (Node) 클래스 정의

```java
class Node {
    int data;   // 노드가 저장할 데이터
    Node next;  // 다음 노드를 가리키는 포인터

    public Node(int data) {
        this.data = data;
        this.next = null; // 초기에는 다음 노드가 없음
    }
}
```

#### 단일 연결 리스트 (SinglyLinkedList) 클래스 구현

```java
public class SinglyLinkedList {
    Node head; // 리스트의 첫 번째 노드를 가리키는 포인터

    public SinglyLinkedList() {
        this.head = null; // 초기에는 리스트가 비어있음
    }

    // 1. 리스트의 끝에 데이터 추가 (add)
    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) { // 리스트가 비어있으면 새 노드가 head가 됨
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) { // 리스트의 마지막 노드까지 이동
                current = current.next;
            }
            current.next = newNode; // 마지막 노드 다음에 새 노드 연결
        }
        System.out.println(data + "가 리스트에 추가되었습니다.");
    }

    // 2. 특정 데이터를 가진 노드 삭제 (delete)
    public void delete(int data) {
        if (head == null) {
            System.out.println("리스트가 비어있어 삭제할 수 없습니다.");
            return;
        }

        if (head.data == data) { // head 노드를 삭제하는 경우
            head = head.next; // head를 다음 노드로 변경
            System.out.println(data + "가 리스트에서 삭제되었습니다.");
            return;
        }

        Node current = head;
        Node prev = null; // 현재 노드의 이전 노드를 저장하기 위한 포인터
        while (current != null && current.data != data) {
            prev = current;
            current = current.next;
        }

        if (current == null) { // 데이터를 찾지 못한 경우
            System.out.println(data + "를 리스트에서 찾을 수 없어 삭제에 실패했습니다.");
        } else { // 데이터를 찾은 경우
            prev.next = current.next; // 이전 노드의 next를 현재 노드의 next로 연결하여 현재 노드를 건너뛰게 함
            System.out.println(data + "가 리스트에서 삭제되었습니다.");
        }
    }

    // 3. 특정 데이터를 가진 노드 탐색 (search)
    public boolean search(int data) {
        Node current = head;
        while (current != null) {
            if (current.data == data) {
                System.out.println(data + "를 리스트에서 찾았습니다.");
                return true;
            }
            current = current.next;
        }
        System.out.println(data + "를 리스트에서 찾을 수 없습니다.");
        return false;
    }

    // 4. 리스트의 모든 요소 출력 (printList)
    public void printList() {
        if (head == null) {
            System.out.println("리스트가 비어있습니다.");
            return;
        }
        Node current = head;
        System.out.print("리스트: ");
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        System.out.println("--- 단일 연결 리스트 예제 ---");
        SinglyLinkedList list = new SinglyLinkedList();

        list.add(10);
        list.add(20);
        list.add(30);
        list.printList(); // 리스트: 10 -> 20 -> 30 -> null

        list.search(20); // 20을 리스트에서 찾았습니다.
        list.search(40); // 40을 리스트에서 찾을 수 없습니다.

        list.delete(20); // 20이 리스트에서 삭제되었습니다.
        list.printList(); // 리스트: 10 -> 30 -> null

        list.delete(10); // 10이 리스트에서 삭제되었습니다.
        list.printList(); // 리스트: 30 -> null

        list.delete(50); // 50을 리스트에서 찾을 수 없어 삭제에 실패했습니다.
        list.printList(); // 리스트: 30 -> null

        list.delete(30); // 30이 리스트에서 삭제되었습니다.
        list.printList(); // 리스트가 비어있습니다.

        list.delete(100); // 리스트가 비어있어 삭제할 수 없습니다.
    }
}
```

-----

## 2\. 이중 연결 리스트 (Doubly Linked List)

### 개념

**이중 연결 리스트**는 단일 연결 리스트와 다르게 **양방향으로 탐색이 가능한 리스트**이다. 각 노드는 **데이터**, **다음 노드를 가리키는 `next` 포인터**, 그리고 **이전 노드를 가리키는 `prev` 포인터**로 구성된다. `head`와 함께 리스트의 가장 뒤를 가리키는 **`tail`** 포인터도 유지하여 마지막 노드에 빠르게 접근할 수 있다.

양방향으로 탐색이 가능하기 때문에 특정 노드의 이전 노드를 찾는 과정이 단일 연결 리스트보다 훨씬 효율적이다. 삽입과 삭제 연산 또한 단일 연결 리스트에 비해 좀 더 복잡하지만, `prev` 포인터 덕분에 유연하게 처리할 수 있다. 다만, 각 노드가 포인터를 하나 더 가지고 있어 메모리 사용량이 단일 연결 리스트보다 많다는 단점이 있다.

### 구조 (Diagram)

```
null <- [Head (Data|Prev|Next)] <-> [Node 1 (Data|Prev|Next)] <-> ... <-> [Node N (Data|Prev|Next)] -> null
                                                                                   [Tail]
```

### Java 코드 예시

#### 노드 (DNode) 클래스 정의

```java
class DNode {
    int data;   // 노드가 저장할 데이터
    DNode next; // 다음 노드를 가리키는 포인터
    DNode prev; // 이전 노드를 가리키는 포인터

    public DNode(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
```

#### 이중 연결 리스트 (DoublyLinkedList) 클래스 구현

```java
public class DoublyLinkedList {
    DNode head; // 리스트의 첫 번째 노드
    DNode tail; // 리스트의 마지막 노드

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    // 1. 리스트의 끝에 데이터 추가 (add)
    public void add(int data) {
        DNode newNode = new DNode(data);
        if (head == null) { // 리스트가 비어있으면 새 노드가 head이자 tail이 됨
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode; // 현재 tail의 next를 새 노드로 연결
            newNode.prev = tail; // 새 노드의 prev를 현재 tail로 연결
            tail = newNode;      // tail을 새 노드로 업데이트
        }
        System.out.println(data + "가 리스트에 추가되었습니다.");
    }

    // 2. 특정 데이터를 가진 노드 삭제 (delete)
    public void delete(int data) {
        if (head == null) {
            System.out.println("리스트가 비어있어 삭제할 수 없습니다.");
            return;
        }

        DNode current = head;
        while (current != null && current.data != data) {
            current = current.next;
        }

        if (current == null) { // 데이터를 찾지 못한 경우
            System.out.println(data + "를 리스트에서 찾을 수 없어 삭제에 실패했습니다.");
        } else { // 데이터를 찾은 경우
            if (current.prev != null) { // 현재 노드의 이전 노드가 있다면
                current.prev.next = current.next; // 이전 노드의 next를 현재 노드의 next로 연결
            } else { // 현재 노드가 head인 경우 (이전 노드가 없음)
                head = current.next; // head를 다음 노드로 변경
            }

            if (current.next != null) { // 현재 노드의 다음 노드가 있다면
                current.next.prev = current.prev; // 다음 노드의 prev를 현재 노드의 prev로 연결
            } else { // 현재 노드가 tail인 경우 (다음 노드가 없음)
                tail = current.prev; // tail을 이전 노드로 변경
            }
            System.out.println(data + "가 리스트에서 삭제되었습니다.");
        }
    }

    // 3. 특정 데이터를 가진 노드 탐색 (search) - 정방향
    public boolean search(int data) {
        DNode current = head;
        while (current != null) {
            if (current.data == data) {
                System.out.println(data + "를 리스트에서 찾았습니다.");
                return true;
            }
            current = current.next;
        }
        System.out.println(data + "를 리스트에서 찾을 수 없습니다.");
        return false;
    }

    // 4. 리스트의 모든 요소 출력 (printListForward) - 정방향
    public void printListForward() {
        if (head == null) {
            System.out.println("리스트가 비어있습니다.");
            return;
        }
        DNode current = head;
        System.out.print("리스트 (정방향): ");
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // 5. 리스트의 모든 요소 출력 (printListBackward) - 역방향
    public void printListBackward() {
        if (tail == null) {
            System.out.println("리스트가 비어있습니다.");
            return;
        }
        DNode current = tail;
        System.out.print("리스트 (역방향): ");
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.prev;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        System.out.println("\n--- 이중 연결 리스트 예제 ---");
        DoublyLinkedList dList = new DoublyLinkedList();

        dList.add(100);
        dList.add(200);
        dList.add(300);
        dList.printListForward();  // 리스트 (정방향): 100 <-> 200 <-> 300 <-> null
        dList.printListBackward(); // 리스트 (역방향): 300 <-> 200 <-> 100 <-> null

        dList.search(200); // 200을 리스트에서 찾았습니다.
        dList.search(400); // 400을 리스트에서 찾을 수 없습니다.

        dList.delete(200); // 200이 리스트에서 삭제되었습니다.
        dList.printListForward();  // 리스트 (정방향): 100 <-> 300 <-> null
        dList.printListBackward(); // 리스트 (역방향): 300 <-> 100 <-> null

        dList.delete(100); // 100이 리스트에서 삭제되었습니다.
        dList.printListForward();  // 리스트 (정방향): 300 <-> null
        dList.printListBackward(); // 리스트 (역방향): 300 <-> null

        dList.delete(500); // 500을 리스트에서 찾을 수 없어 삭제에 실패했습니다.
        dList.printListForward();  // 리스트 (정방향): 300 <-> null

        dList.delete(300); // 300이 리스트에서 삭제되었습니다.
        dList.printListForward();  // 리스트가 비어있습니다.
        dList.printListBackward(); // 리스트가 비어있습니다.

        dList.delete(1000); // 리스트가 비어있어 삭제할 수 없습니다.
    }
}
```

-----
