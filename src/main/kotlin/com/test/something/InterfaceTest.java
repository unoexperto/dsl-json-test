package com.test.something;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;

import java.util.List;

public class InterfaceTest {

	@CompiledJson
	public static class HasInterface {
		@JsonAttribute(index = 3)
		public int x;
		@JsonAttribute(index = 1)
		public Iface1 i;
		@JsonAttribute(index = 4)
		public List<Iface1> ii;
		@JsonAttribute(index = 2)
		public Iface2 c;
	}

	public interface Iface1 {
		int y();
		void y(int y);
	}

	@CompiledJson(discriminator = "@type")
	public interface Iface2 {
		int y();
		void y(int y);
	}

	@CompiledJson
	public static class IsIfaceDefault1 implements Iface1 {
		private int y;

		public int y() {
			return y;
		}

		public void y(int y) {
			this.y = y;
		}

		public IsIfaceDefault1(int y) {
			this.y = y;
		}
	}

	@CompiledJson(name = "custom-name")
	public static class IsIfaceCustom1 implements Iface1 {
		private int y;

		public int y() {
			return y;
		}

		public void y(int y) {
			this.y = y;
		}

		public IsIfaceCustom1(int y) {
			this.y = y;
		}
	}

	@CompiledJson
	public static class IsIfaceDefault2 implements Iface2 {
		private int y;

		public int y() {
			return y;
		}

		public void y(int y) {
			this.y = y;
		}

		public IsIfaceDefault2(int y) {
			this.y = y;
		}
	}

	@CompiledJson(name = "custom")
	public static class IsIfaceCustom2 implements Iface2 {
		private int y;

		public int y() {
			return y;
		}

		public void y(int y) {
			this.y = y;
		}

		public List<Iface2> list;

		public IsIfaceCustom2(int y, List<Iface2> list) {
			this.y = y;
			this.list = list;
		}
	}
}
