import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author onegines
 * @date 08.09.2026
 */
public class ParentList<T> {

    protected List<T> list;

    // Курсор всегда указывает на некоторый элемент, если список не пустой
    protected int currentCursorPosition = 0;


    // Статусы addToEmpty()
    public static final int ADD_TO_EMPTY_NIL = 0;
    public static final int ADD_TO_EMPTY_OK = 1;
    public static final int ADD_TO_EMPTY_ERR = 2;

    // Статусы putRight()
    public static final int PUT_RIGHT_NIL = 0;
    public static final int PUT_RIGHT_OK = 1;
    public static final int PUT_RIGHT_ERR = 2;

    // Статусы putLeft()
    public static final int PUT_LEFT_NIL = 0;
    public static final int PUT_LEFT_OK = 1;
    public static final int PUT_LEFT_ERR = 2;

    // Статусы remove()
    public static final int REMOVE_NIL = 0;
    public static final int REMOVE_OK = 1;
    public static final int REMOVE_ERR = 2;

    // Статусы get()
    public static final int GET_NIL = 0;
    public static final int GET_OK = 1;
    public static final int GET_ERR = 2;

    // Статусы find()
    public static final int FIND_NIL = 0;
    public static final int FIND_OK = 1;
    public static final int FIND_ERR = 2;
    public static final int FIND_EMPTY = 3;

    // Статусы head()
    public static final int HEAD_NIL = 0;
    public static final int HEAD_OK = 1;
    public static final int HEAD_ERR = 2;

    // Статусы tail()
    public static final int TAIL_NIL = 0;
    public static final int TAIL_OK = 1;
    public static final int TAIL_ERR = 2;

    // Статусы right()
    public static final int RIGHT_NIL = 0;
    public static final int RIGHT_OK = 1;
    public static final int RIGHT_ERR = 2;

    // Статусы replace()
    public static final int REPLACE_NIL = 0;
    public static final int REPLACE_OK = 1;
    public static final int REPLACE_ERR = 2;


    private int findStatus;
    private int addToEmptyStatus;
    private int putRightStatus;
    private int putLeftStatus;
    private int removeStatus;
    private int getStatus;
    private int headStatus;
    private int tailStatus;
    private int rightStatus;
    private int replaceStatus;


    // Конструктор
    // постусловие: создан новый пустой список
    public ParentList() {
        list = new ArrayList<>();

        findStatus = FIND_NIL;
        addToEmptyStatus = ADD_TO_EMPTY_NIL;
        putRightStatus = PUT_RIGHT_NIL;
        putLeftStatus = PUT_LEFT_NIL;
        removeStatus = REMOVE_NIL;
        getStatus = GET_NIL;
        headStatus = HEAD_NIL;
        tailStatus = TAIL_NIL;
        rightStatus = RIGHT_NIL;
        replaceStatus = REPLACE_NIL;
    }


    // Команды:
    // предусловие: список не пуст
    // постусловие: курсор установлен на первый элемент
    public void head() {
        if (!list.isEmpty()) {
            currentCursorPosition = 0;
            headStatus = HEAD_OK;
        } else {
            headStatus = HEAD_ERR;
        }
    }

    // предусловие: список не пуст
    // постусловие: курсор установлен на последний элемент
    public void tail() {
        if (!list.isEmpty()) {
            currentCursorPosition = list.size() - 1;
            tailStatus = TAIL_OK;
        } else {
            tailStatus = TAIL_ERR;
        }
    }

    // предусловие: правее курсора есть элемент
    // постусловие: курсор сдвинут на один элемент вправо
    public void right() {
        if (!list.isEmpty()
                && currentCursorPosition < list.size() - 1) {

            currentCursorPosition++;
            rightStatus = RIGHT_OK;
        } else {
            rightStatus = RIGHT_ERR;
        }
    }

    // предусловие: список не пуст
    // постусловие: справа от текущего элемента добавлен новый элемент
    public void putRight(T value) {
        if (!list.isEmpty()) {
            list.add(currentCursorPosition + 1, value);
            putRightStatus = PUT_RIGHT_OK;
        } else {
            putRightStatus = PUT_RIGHT_ERR;
        }
    }

    // предусловие: список не пуст
    // постусловие: слева от текущего элемента добавлен новый элемент
    public void putLeft(T value) {
        if (!list.isEmpty()) {
            list.add(currentCursorPosition, value);

            // Курсор остаётся на прежнем текущем элементе
            currentCursorPosition++;

            putLeftStatus = PUT_LEFT_OK;
        } else {
            putLeftStatus = PUT_LEFT_ERR;
        }
    }


    // предусловие: список пуст
    // постусловие: в списке один элемент
    public void addToEmpty(T value) {
        if (list.isEmpty()) {
            list.add(value);
            currentCursorPosition = 0;
            addToEmptyStatus = ADD_TO_EMPTY_OK;
        } else {
            addToEmptyStatus = ADD_TO_EMPTY_ERR;
        }
    }


    // предусловие: список не пуст
    // постусловие: текущий элемент удалён, курсор перемещён вправо, если возможно, иначе влево
    public void remove() {
        if (!list.isEmpty()) {

            list.remove(currentCursorPosition);

            // Если удалён хвост, перемещаемся на предыдущий элемент
            if (!list.isEmpty()
                    && currentCursorPosition == list.size()) {

                currentCursorPosition--;
            }

            // Если список стал пустым
            if (list.isEmpty()) {
                currentCursorPosition = 0;
            }

            removeStatus = REMOVE_OK;

        } else {
            removeStatus = REMOVE_ERR;
        }
    }


    // постусловие: список очищен
    public void clear() {
        list.clear();
        currentCursorPosition = 0;
    }


    // постусловие: новый элемент добавлен в хвост списка
    public void addTail(T value) {
        list.add(value);

        if (list.size() == 1) {
            currentCursorPosition = 0;
        }
    }

    // постусловие: удалены все элементы со значением value
    public void removeAll(T value) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (Objects.equals(list.get(i), value)) {
                list.remove(i);
            }
        }
        currentCursorPosition = 0;
    }

    // предусловие: список не пуст
    // постусловие: значение текущего элемента заменено
    public void replace(T value) {
        if (!list.isEmpty()) {
            list.set(currentCursorPosition, value);
            replaceStatus = REPLACE_OK;
        } else {
            replaceStatus = REPLACE_ERR;
        }
    }

    // постусловие: курсор установлен на следующий элемент с заданным значением, если такой найден
    public void find(T value) {

        if (list.isEmpty()) {
            findStatus = FIND_EMPTY;
            return;
        }

        for (int i = currentCursorPosition + 1;
             i < list.size();
             i++) {

            if (Objects.equals(list.get(i), value)) {
                currentCursorPosition = i;
                findStatus = FIND_OK;
                return;
            }
        }

        findStatus = FIND_ERR;
    }

    // Запросы:
    // предусловие: список не пуст
    public T get() {
        if (!list.isEmpty()) {
            getStatus = GET_OK;
            return list.get(currentCursorPosition);
        }

        getStatus = GET_ERR;
        return null;
    }

    public boolean isHead() {
        return !list.isEmpty()
                && currentCursorPosition == 0;
    }

    public boolean isTail() {
        return !list.isEmpty()
                && currentCursorPosition == list.size() - 1;
    }

    public boolean isValue() {
        return !list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    // Гетеры статусов:
    public int getHeadStatus() {
        return headStatus;
    }

    public int getTailStatus() {
        return tailStatus;
    }

    public int getRightStatus() {
        return rightStatus;
    }

    public int getPutRightStatus() {
        return putRightStatus;
    }

    public int getPutLeftStatus() {
        return putLeftStatus;
    }

    public int getAddToEmptyStatus() {
        return addToEmptyStatus;
    }

    public int getRemoveStatus() {
        return removeStatus;
    }

    public int getReplaceStatus() {
        return replaceStatus;
    }

    public int getFindStatus() {
        return findStatus;
    }

    public int getGetStatus() {
        return getStatus;
    }
}