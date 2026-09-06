import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author onegines
 * @date 04.09.2026
 */
public class LinkedList<T> {
    private List<T> list;

    //всегда указывает на некоторый узел, если список непустой
    private int currentСursorPosition = 0;

    // Константы статусов
    public static final int ADD_TO_EMPTY_NIL = 0;
    public static final int ADD_TO_EMPTY_OK = 1; // addToEmpty() отработал нормально
    public static final int ADD_TO_EMPTY_ERR = 2; // список не пуст

    public static final int PUT_RIGHT_NIL = 0; // addToEmpty() еще не вызывался
    public static final int PUT_RIGHT_OK = 1; // putRight() отработал нормально
    public static final int PUT_RIGHT_ERR = 2; // список пуст

    public static final int PUT_LEFT_NIL = 0; // addToEmpty() еще не вызывался
    public static final int PUT_LEFT_OK = 1; // putLeft() отработал нормально
    public static final int PUT_LEFT_ERR = 2; // список пуст

    // Статусы для remove()
    public static final int REMOVE_NIL = 0; // addToEmpty() еще не вызывался
    public static final int REMOVE_OK = 1; // remove() отработал нормально
    public static final int REMOVE_ERR = 2; // список пуст

    // Статусы для get()
    public static final int GET_NIL = 0; // addToEmpty() еще не вызывался
    public static final int GET_OK = 1; // get() отработал нормально
    public static final int GET_ERR = 2; // список пуст

    // Статусы курсора
    public static final int CURSOR_IS_HEAD = 1;
    public static final int CURSOR_IS_TAIL = 2;
    public static final int CURSOR_IS_VALUE = 3;

    public static final int FIND_NIL = 0;
    public static final int FIND_OK = 1;
    public static final int FIND_ERR = 2;

    public static final int HEAD_NIL = 0;
    public static final int HEAD_OK = 1;
    public static final int HEAD_ERR = 2;

    public static final int TAIL_NIL = 0;
    public static final int TAIL_OK = 1;
    public static final int TAIL_ERR = 2;

    public static final int RIGHT_NIL = 0;
    public static final int RIGHT_OK = 1;
    public static final int RIGHT_ERR = 2;

    public static final int REPLACE_NIL = 0;
    public static final int REPLACE_OK = 1;
    public static final int REPLACE_ERR = 2;
    private int find_status;
    private int add_to_empty_status;
    private int put_right_status;
    private int put_left_status;
    private int remove_status;
    private int get_status;
    private int head_status;
    private int tail_status;
    private int right_status;
    private int replace_status;

    // Конструктор по умолчанию
    // постусловие: создан новый пустой список
    public LinkedList() {
        list = new ArrayList<>();

        find_status = FIND_NIL;
        add_to_empty_status = ADD_TO_EMPTY_NIL;
        put_right_status = PUT_RIGHT_NIL;
        put_left_status = PUT_LEFT_NIL;
        remove_status = REMOVE_NIL;
        get_status = GET_NIL;
        head_status = HEAD_NIL;
        tail_status = TAIL_NIL;
        right_status = RIGHT_NIL;
        replace_status = REPLACE_NIL;
    }


    // команды:
    // предусловие: список пустой;
    // постусловие: в списко добавлено новое значение
    public void addToEmpty(T value) {
        if (list.size() == 0) {
            list.add(value);
            currentСursorPosition = 0;
            add_to_empty_status = ADD_TO_EMPTY_OK;
        } else {
            add_to_empty_status = ADD_TO_EMPTY_ERR;
        }
    }

    // предусловие: список не пустой;
    // постусловие: в хвост списка добавлено значение
    public void addTail(T value) {
        list.add(value);
        if (list.size() == 1) {
            currentСursorPosition = 0;
        }

    }

    // предусловие: список не пустой;
    // постусловие: значение с индексом = текущему положению курсора заменено на value
    public void replace(T value) {
        replace_status = REPLACE_ERR;
        if (list.size() != 0) {
            list.set(currentСursorPosition, value);
            replace_status = REPLACE_OK;
        }
    }

    // предусловие: список не пустой;
    // постусловие: размер списка увеличен на 1, узлы с индексом большим текущего положения курсора смещены вправо, на позицию
    // с индексом currentСursorPosition+1 доабвлено значение value
    public void putRight(T value) {
        put_right_status = PUT_RIGHT_ERR;
        if (list.size() != 0) {
            list.add(currentСursorPosition + 1, value);
            put_right_status = PUT_RIGHT_OK;
        }
    }

    // предусловие: список не пустой;
    // постусловие: размер списка увеличен на 1, узлы с индексом большим или равным текущего положения курсора смещены вправо, на позицию
    // с индексом currentСursorPosition добавлено значение value
    public void putLeft(T value) {
        if (!list.isEmpty()) {
            list.add(currentСursorPosition, value);
            currentСursorPosition++;
            put_left_status = PUT_LEFT_OK;
        } else {
            put_left_status = PUT_LEFT_ERR;
        }
    }

    // постусловие: из списка удаляются все значения = value, индекс курсора = 0
    public void remove_all(T value) {
        for (int i = size() - 1; i >= 0; i--) {
            if (Objects.equals(list.get(i), value)) {
                list.remove(i);
            }
        }
        currentСursorPosition = 0;
    }

    public void toHead() {
        head_status = HEAD_ERR;
        if (list.size() != 0) {
            currentСursorPosition = 0;
            head_status = HEAD_OK;
        }
    }

    // предусловие: список не пустой;
    public void toTail() {
        tail_status = TAIL_ERR;
        if (list.size() != 0) {
            currentСursorPosition = list.size() - 1;
            tail_status = TAIL_OK;
        }
    }

    // предусловие: список не пустой;
    public void toRight() {
        right_status = RIGHT_ERR;
        if (!list.isEmpty() && currentСursorPosition < list.size() - 1) {
            currentСursorPosition++;
            right_status = RIGHT_OK;
        }
    }

    // запросы:
    // предусловие: список не пустой;
    public T get() {
        if (!list.isEmpty()) {
            get_status = GET_OK;
            return list.get(currentСursorPosition);
        }
        get_status = GET_ERR;
        return null;
    }

    // предусловие: список не пустой;
    // постусловие: из списка удален элемент с индексом равным текущему положению курсора, если cursorStatus != IS_HEAD,
    // то currentСursorPosition++, если cursorStatus == IS_HEAD, то currentСursorPosition--.
    public void remove() {
        if (list.size() != 0) {

            list.remove(currentСursorPosition);

            if (!list.isEmpty() && currentСursorPosition == list.size()) {
                currentСursorPosition--;
            }

            if (list.isEmpty()) {
                currentСursorPosition = 0;
            }

            remove_status = REMOVE_OK;
        } else {
            remove_status = REMOVE_ERR;
        }
    }

    // постусловие: размер списка == 0, состояни курсора == 0
    public void clear() {
        list.clear();
        currentСursorPosition = 0;
    }

    public int size() {
        return list.size();
    }

    // предусловие: список не пустой;
    // постусловие: индекс курсора установлен на следующий узел по отношению к текущему
    public void find(T value) {
        find_status = FIND_ERR;
        if (list.size() != 0) {
            for (int i = currentСursorPosition + 1; i < list.size(); i++) {
                if (Objects.equals(list.get(i), value)) {
                    currentСursorPosition = i;
                    find_status = FIND_OK;
                    return;
                }
            }

        }
    }

    public boolean isHead() {
        return !list.isEmpty() && currentСursorPosition == 0;
    }

    public boolean isTail() {
        return !list.isEmpty() && currentСursorPosition == list.size() - 1;
    }

    public boolean isValue() {
        if (size() != 0) {
            return true;
        }
        return false;
    }

    public int getFind_status() {
        return find_status;
    }

    public int getAdd_to_empty_status() {
        return add_to_empty_status;
    }

    public int getPut_right_status() {
        return put_right_status;
    }

    public int getPut_left_status() {
        return put_left_status;
    }

    public int getRemove_status() {
        return remove_status;
    }

    public int getGet_status() {
        return get_status;
    }

    public int getHead_status() {
        return head_status;
    }

    public int getTail_status() {
        return tail_status;
    }

    public int getRight_status() {
        return right_status;
    }

    public int getReplace_status() {
        return replace_status;
    }
}
