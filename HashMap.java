import java.util.ArrayList;
public class HashMap<K, V> implements Map<K, V> {

    public class Node {  //узлы массива
        public int hash;
        public K key;
        public V value;
        Node next;
        Node prev;

        Node (K key, V value) { //конструктор узлов
            this.key = key;
            this.value = value;
            this.hash = this.hashCode();
        }
    }

    public ArrayList<LinkedNode> array; //массив из листов

    HashMap () {;} //создание пустой коллекции

    @Override
    public void put(K key, V value) {
        Node node = new Node(key, value); //создаём узел, который будем добавлять в массив
        if (!containsKey(key)) { //если такого ключа еще нет в HashMap, можем добавлять элемент
            int hashcode = hashCode(value);
            boolean flag = false;

            for (int i = 0; i < this.array.size(); i ++) { //ищем лист с нужным нам хэш-кодом
                if (this.array.get(i).hash == hashcode ) {
                    this.array.get(i).addNode(node);
                    flag = true;
                }
            }
            if (!flag) { //если в массиве нет листа с нужным нам хэш-кодом, создаем новый лист в массиве
                LinkedNode linked_node = new LinkedNode();
                linked_node.hash = hashcode;
                int arraySize = this.array.size();
                this.array.get(arraySize).addNode(node);
            }
        } else {
            System.out.println("Значение с таким ключом уже присутствует в HashMap");
        }
    }

    @Override
    public V get(K key) {
        Node current = this.array.get(0).tale; //начинаем исктать с первого элемента в нклевом листе
        int counterOfLists = 0;
        while (current.key != key) {
            if (current == this.array.get(counterOfLists).head) { //если мы дошли до головы листа в первом элементе, переопределяем хвост
                counterOfLists++;
                current = this.array.get(counterOfLists).tale;
            }
            current = current.next;
        }
        return current.value;
    }

    @Override
    public boolean containsKey(K key) {
        Node current = this.array.get(0).tale; //начинаем исктать с первого элемента в нклевом листе
        int counterOfLists = 0;
        boolean flag = false;
        for (int i = 0; i < this.size(); i++) {
            if (current == this.array.get(counterOfLists).head) { //если мы дошли до головы листа в первом элементе, переопределяем хвост
                counterOfLists++;
                current = this.array.get(counterOfLists).tale;
            }
            if (current.key == key) { //если нашли необходимое значение, выхлдим из цикла
                flag = true;
                break;
            }
            current = current.next;
        }
        return flag;
    }

    @Override
    public boolean containsValue(V value) {
        Node current = this.array.get(0).tale; //начинаем исктать с первого элемента в нулевом листе
        int counterOfLists = 0;
        boolean flag = false;

        for (int i = 0; i < this.size(); i++) {
            if (current == this.array.get(counterOfLists).head) { //если мы дошли до головы листа в первом элементе, переопределяем хвост
                counterOfLists++;
                current = this.array.get(counterOfLists).tale;
            }
            if (current.value == value) { //если нашли необходимое значение, выхлдим из цикла
                flag = true;
                break;
            }
            current = current.next;
        }
        return flag;
    }

    @Override
    public void remove(K key) {
        Node current = this.array.get(0).tale; //начинаем исктать с первого элемента в нклевом листе
        int counterOfLists = 0;
        while (current.key != key) {
            if (current == this.array.get(counterOfLists).head) { //если мы дошли до головы листа в первом элементе, переопределяем хвост
                counterOfLists++;
                current = this.array.get(counterOfLists).tale;
            }
            current = current.next;
        }

        (current.prev).next = current.next; //переопределяем указатели последующего и предыдущего элементов
        (current.next).prev = current.prev;

        current.prev = null;
        current.next = null;
    }

    @Override
    public int size() {
        int counterOfNodes = 0; //счетчик эдементов коллекции
        Node current;

        for (int i = 0; i < this.array.size(); i++) { //проходимся по каждому листу
            current = this.array.get(i).tale; //перед каждым прохождением по листу, переопределяем текущий элемент
            while (current != this.array.get(i).head) { //пока не дойдем до головы списка
                counterOfNodes ++;
                current = current.next;
            }
        }
        return counterOfNodes;
    }

    @Override
    public int hashCode(V value) {
        // Преобразование объекта в строку
        String str = value.toString();
        int sum = 0;

        for (int i = 0; i < str.length(); i++) {
            int ascii = (int) str.charAt(i); //получение кода ASCII символа
            sum += ascii;
        }
        return sum;
    }

    @Override
    public boolean equals(K key1, K key2) {
        if (get(key1) == get(key2)) {
            return true;
        } else {
            return false;
        }
    }

}
