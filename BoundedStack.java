import java.util.ArrayList;
import java.util.List;

/**
 * @author onegines
 * @date 31.08.2026
 */
public class BoundedStack<T> {
    private List<T> stack; // основное хранилище стека
    public static final int POP_NIL = 0;
    public static final int POP_OK = 1;
    public static final int POP_ERR = 2;
    public static final int PEEK_NIL = 0;
    public static final int PEEK_OK = 1;
    public static final int PEEK_ERR = 2;
    // Статусы push()
    public static final int PUSH_NIL = 0;
    public static final int PUSH_OK = 1;
    public static final int PUSH_ERR = 2;
    // Максимальный размер стека
    private final int maxSize;

    private int peek_status; // статус запроса peek()
    private int pop_status; // статус команды pop()
    private int push_status;

    // Конструктор по умолчанию
    public BoundedStack() {
        this(32);
    }

    // Конструктор с ограничением размера
    public BoundedStack(int maxSize) {

        if (maxSize <= 0) {
            throw new IllegalArgumentException(
                    "Размер стека должен быть положительным"
            );
        }

        this.maxSize = maxSize;
        this.stack = new ArrayList<>();

        this.pop_status = POP_NIL;
        this.peek_status = PEEK_NIL;
        this.push_status = PUSH_NIL;
    }

    // Добавление элемента
    public void push(T value) {

        if (size() < maxSize) {
            stack.add(value);
            push_status = PUSH_OK;
        } else {
            push_status = PUSH_ERR;
        }
    }

    public void pop() {
        if (this.stack.size() > 0) {
            stack.remove(size() - 1);
            pop_status = POP_OK;
        } else {
            pop_status = POP_ERR;
        }
    }

    public void clear() {
        stack.clear();
        // начальные статусы для предусловий
        peek_status = PEEK_NIL;
        pop_status = POP_NIL;
        push_status = PUSH_NIL;
    }

    public T peek() {
        if (size() > 0) {
            peek_status = PEEK_OK;
            return stack.get(size() - 1);
        } else {
            peek_status = PEEK_ERR;
            return null;
        }
    }

    public int size() {
        return stack.size();
    }

    public int getPeek_status() {
        return peek_status;
    }

    public int getPop_status() {
        return pop_status;
    }

    public int getPush_status() {
        return push_status;
    }
}
