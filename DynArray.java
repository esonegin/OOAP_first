/**
 * @author onegines
 * @date 11.09.2026
 */
public class DynArray<T> {

    private T[] array;

    // Текущее количество элементов
    private int count;

    // Размер выделенного буфера
    private int capacity;

    // Минимальная ёмкость
    private static final int MIN_CAPACITY = 16;


    // Конструктор
    // постусловие: создан пустой динамический массив
    // count = 0
    // capacity = 16
    public DynArray() {
        count = 0;
        capacity = MIN_CAPACITY;
        array = (T[]) new Object[capacity];
    }


    // Внутренняя реализация изменения размера буфера
    private void makeArray(int newCapacity) {
        T[] newArray = (T[]) new Object[newCapacity];
        for (int i = 0; i < count; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
        capacity = newCapacity;
    }


    // Запрос элемента
    // предусловие: 0 <= i < count
    public T getItem(int i) {
        if (i < 0 || i >= count) {
            throw new IndexOutOfBoundsException(
                    "Индекс находится вне границ массива: " + i
            );
        }
        return array[i];
    }

    // Добавление элемента в хвост
    // постусловие: item добавлен в хвост
    // count увеличен на 1
    // при необходимости capacity увеличена в 2 раза
    public void append(T item) {
        if (count == capacity) {
            makeArray(capacity * 2);
        }
        array[count] = item;
        count++;
    }

    // Вставка элемента
    // предусловие: 0 <= i <= count
    // постусловие: item вставлен на позицию i
    public void insert(T item, int i) {
        if (i < 0 || i > count) {
            throw new IndexOutOfBoundsException(
                    "Индекс находится вне допустимых границ: " + i
            );
        }
        if (count == capacity) {
            makeArray(capacity * 2);
        }
        for (int j = count; j > i; j--) {
            array[j] = array[j - 1];
        }
        array[i] = item;
        count++;
    }


    // Удаление элемента
    // предусловие: 0 <= i < count
    // постусловие: элемент удалён
    // count уменьшен на 1
    // при необходимости capacity уменьшена
    public void remove(int i) {
        if (i < 0 || i >= count) {
            throw new IndexOutOfBoundsException(
                    "Индекс находится вне границ массива: " + i
            );
        }
        for (int j = i; j < count - 1; j++) {
            array[j] = array[j + 1];
        }
        array[count - 1] = null;
        count--;
        // Сжимаем буфер, если заполненность стала строго меньше 50%
        if (count < capacity * 0.5 && capacity > MIN_CAPACITY) {
            int newCapacity = (int) (capacity / 1.5);
            if (newCapacity < MIN_CAPACITY) {
                newCapacity = MIN_CAPACITY;
            }
            // Новая capacity не должна быть меньше count
            if (newCapacity < count) {
                newCapacity = count;
            }
            makeArray(newCapacity);
        }
    }

    // Количество элементов
    public int getCount() {
        return count;
    }

    // Текущая ёмкость буфера
    public int getCapacity() {
        return capacity;
    }
}
