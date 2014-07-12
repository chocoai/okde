package net.cedu.model.common;

/**
 * 表示范围的抽象类
 * @author Sauntor
 *
 * @param <T>
 */
public abstract class Range<T> {
	protected T begin;
	protected T end;
	protected InOutWard toward = InOutWard.In;
	
	public T getBegin() {
		return begin;
	}

	public void setBegin(T begin) {
		this.begin = begin;
	}

	public T getEnd() {
		return end;
	}

	public void setEnd(T end) {
		this.end = end;
	}

	public InOutWard getToward() {
		return toward;
	}

	public void setToward(InOutWard toward) {
		this.toward = toward;
	}

	public static enum InOutWard { In, Out }
}
