public class HashMap<K,V> {
    private Node <K,V> table [];
    static final int DEFAULT_TABLE_CAPACITY = 16;
    class Node<K,V>{
        final int hash;
        final K key;
         V value;
        Node <K, V> next;
        Node <K, V> prev;
        Node(K key, V value) {
            this.hash = hashCode();
            this.key = key;
            this.value = value;
        }
        public K getKey(){
            return this.key;
        }
        public V getValue(){
            return this.value;
        }
        public void setValue(V value){ this.value=value;}
    }

    HashMap(){
        table = new Node[DEFAULT_TABLE_CAPACITY];
    }

    public void put(K key, V value){ // добавление обьекта
        int hash = key.hashCode() % DEFAULT_TABLE_CAPACITY; //высчитываем хэш
        Node <K,V> dvizhok = table[hash];

        if (dvizhok==null){
            table[hash] = new Node<K, V>(key, value);
        } else {
            while(dvizhok.next!=null){
                if (dvizhok.getKey()==key){
                    dvizhok.setValue(value); //если ключ совпадет меняем значение
                    return;
                }
                dvizhok=dvizhok.next; //передвигаем движок
            }
            if (dvizhok.getKey()==key){ //если добрались до последнего движка
                dvizhok.setValue(value); //если ключ совпадет меняем значение
                return;
            }
            dvizhok.next=new Node<K,V>(key,value); //если дошли до последнего движка и одинакового не нашлось
        }
    }

    public boolean containsValue(V value){ //проверка на содержание данного значения
        for(int i=0;i<DEFAULT_TABLE_CAPACITY;i++){
            Node <K,V> dvizhok = table[i];
            while(dvizhok.next!=null){
                if(dvizhok.getValue()==value){
                    return true;
                }
            }
        } return false;
    }

    public boolean containsKey(K key){//проверка на содержание данного ключа
        for(int i=0;i<DEFAULT_TABLE_CAPACITY;i++){
            Node <K,V> dvizhok = table[i];
            while(dvizhok.next!=null){
                if(dvizhok.getKey()==key){
                    return true;
                }
            }
        } return false;
    }

    public V get(K key){ //нахождение значения по ключу обьекта
        int hash = key.hashCode() % DEFAULT_TABLE_CAPACITY;
        Node <K,V> dvizhok = table[hash]; //нашли подходящий бакет

        if (dvizhok==null){ //если  бакет пустой
            return null;
        }
        while(dvizhok!=null){
            if(dvizhok.getKey()==key){
                return dvizhok.getValue();
            }
            dvizhok=dvizhok.next;
        }
        return null; //соответствующие значение не найдено
    }

    public void remove(K key){ //удаление обьекта по ключу
        int hash = key.hashCode() % DEFAULT_TABLE_CAPACITY;
        Node <K,V> dvizhok = table[hash];

        if (dvizhok==null){ //если  бакет пустой
            return;
        }

        if (dvizhok.getKey()==key){ //если найденный обьект в начале бакета
            table[hash]=dvizhok.next;
            dvizhok.next=null;
            return;
        }

        while(dvizhok !=  null){ //если найденный обьект в середине бакета
            if(dvizhok.getKey()==key){
                dvizhok.prev=dvizhok.next;
                dvizhok.next=null;
                return;
            }
            dvizhok.prev=dvizhok;
            dvizhok=dvizhok.next;
        }
        return; //если такого обекта нет
    }

    public boolean equals(K key1, K key2) {
        if (get(key1) == get(key2)) {
            return true;
        } else {
            return false;
        }
    }

}
