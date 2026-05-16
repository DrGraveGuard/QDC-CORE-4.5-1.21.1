package com.qdc_mod.qdc_core_4_5.api;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class GuiFunctions {

	public static List<Point> generateIconPosList(int numOfItems, int numOfCols, Point iconSize, Point gap,
			Point startPos) {
		List<Point> res = new ArrayList<Point>();

		int xPos = gap.x;
		int yPos = gap.y;

		int colCounter = 0;
		for (int i = 0; i < numOfItems; i++) {
			colCounter++;

			res.add(new Point(startPos.x + xPos, startPos.y + yPos));
			xPos += iconSize.x;
			xPos += gap.x;
			if (colCounter == numOfCols) {
				yPos += iconSize.y;
				yPos += gap.y;
				xPos = gap.x;
				colCounter = 0;
			}
		}

		return res;
	}

	public static boolean isHoveringOver(Point windowPos, Point pos, Point size, Point cursor) {
		if (windowPos != null && pos != null && size != null && cursor != null) {
			if (cursor.x > pos.x + windowPos.x && cursor.x < pos.x + windowPos.x + size.x
					&& cursor.y > pos.y + windowPos.y && cursor.y < pos.y + windowPos.y + size.y) {
				return true;
			}
		}
		

		return false;

	}

}
