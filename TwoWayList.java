/**
 * @author onegines
 * @date 08.09.2026
 */
public class TwoWayList<T> extends ParentList<T> {

    // Статусы left()
    public static final int LEFT_NIL = 0;
    public static final int LEFT_OK = 1;
    public static final int LEFT_ERR = 2;

    private int leftStatus;

    public TwoWayList() {
        super();
        leftStatus = LEFT_NIL;
    }

    // предусловие: левее курсора есть элемент
    // постусловие: курсор сдвинут на один узел влево
    public void left() {
        if (!isValue() || isHead()) {
            leftStatus = LEFT_ERR;
        } else {
            currentCursorPosition--;
            leftStatus = LEFT_OK;
        }
    }

    // запрос статуса left()
    public int getLeftStatus() {
        return leftStatus;
    }
}

