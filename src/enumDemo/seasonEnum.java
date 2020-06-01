package enumDemo;

/**
 * 2020/5/19
 *
 * @author wuzhanhao
 * <p>
 * description:
 */
public enum seasonEnum {
    SPRING(1),SUMMER(2),AUTUMN(3),WINTER(4);

    private final int i;

    public int getI() {
        return i;
    }

    seasonEnum(int i) {
        this.i = i;
    }
}
