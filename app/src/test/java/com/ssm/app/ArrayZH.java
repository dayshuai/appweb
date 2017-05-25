package com.ssm.app;

/**
 * 两个数组排列组合
 * 
 * @author huangcheng19079
 *
 */
public class ArrayZH {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] amountArr = { 873, 547, 668, 223 };
		Integer[] countArr = { 6, 2, 3, 3 };
		int allMoney = 0;
		for (int i = 0; i < amountArr.length; i++) {
			allMoney += amountArr[i] * countArr[i];

		}
		caculator(2, amountArr, countArr, allMoney);
	}

	public static void caculator(int averagePeople, Integer[] amountArr, Integer[] countArr, int allMoney) {

		String[] target = turns(getDoubleArr(countArr));
		int minDistans = Integer.valueOf(allMoney);
		String resultStr = "";
		for (String strs : target) {
			int oneMoney = 0;
			int anotherMoney = 0;
			String[] paperCount = strs.split(",");
			int index = 0;
			for (String count : paperCount) {
				oneMoney += Integer.valueOf(count) * amountArr[index];
				index++;
			}
			anotherMoney = allMoney - oneMoney;
			int abs = Math.abs(oneMoney - allMoney / averagePeople) + Math.abs(allMoney / averagePeople - anotherMoney);
			if (minDistans > abs) {
				minDistans = abs;
				resultStr = strs + " one:" + oneMoney + " another:" + anotherMoney;
			}
		}
		System.out.println(resultStr);
	}

	/**
	 * 根据发票张数获得二维数组
	 * 
	 * @param countArr
	 * @return
	 */
	public static String[][] getDoubleArr(Integer[] countArr) {
		String paperCountArr[][] = new String[countArr.length][];
		for (int i = 0; i < countArr.length; i++) {
			String newPaperCountArr[] = new String[countArr[i] + 1];
			for (int j = 0; j <= countArr[i]; j++) {
				newPaperCountArr[j] = j + "";
			}
			paperCountArr[i] = newPaperCountArr;

		}
		return paperCountArr;
	}

	/**
	 * 两两遍历
	 * 
	 * @param array1
	 * @param array2
	 * @return
	 */
	public static String[] doubleTurns(String[] array1, String[] array2) {
		String[] target = new String[array1.length * array2.length];

		for (int i = 0, a1 = 0, a2 = 0; i < array1.length * array2.length; i++) {
			String str = array1[a1] + "," + array2[a2];
			a2++;
			target[i] = str;
			if (a2 == array2.length) {
				a2 = 0;
				a1++;
			}
		}
		return target;

	}

	/**
	 * 遍历组合
	 * 
	 * @param arrays
	 * @return
	 */
	public static String[] turns(String[]... arrays) {
		if (arrays.length == 1) {
			return arrays[0];
		}
		if (arrays.length == 0) {
			return null;
		}
		int count = 1;
		for (int i = 0; i < arrays.length; i++) {
			count *= arrays[i].length;
		}
		String[] target = new String[count];
		for (int i = 0; i < arrays.length; i++) {
			if (i == 0) {
				target = doubleTurns(arrays[0], arrays[1]);
				i++;
			} else {
				target = doubleTurns(target, arrays[i]);
			}

		}
		return target;
	}

	public static int func(int[] array, int[][] result) {
		// array为要组合的数组，size为长度,firstIndex为要放在所以组合最前面的元素,result保存所有组合，每一行为一个组合
		// 所有递归的结果都保存在result中，并且最低层的组合结果保存在最后列
		// 返回本次递归组合的个数
		int size = array.length;
		if (size == 0)// 如果没有元素，不再向下递归
			return 0;
		int num = 0;// 本层递归的总组合数
		int subnum = 0;// 本层每个元素作为头元素，其他元素的组合个数（递归返回）
		for (int i = 0; i < size; i++) {
			int[] newArray = new int[size - 1];
			for (int j = 0, k = 0; j < size; j++) {// 获取待排序子数组
				if (j != i)
					newArray[k++] = array[j];
			}

			subnum = func(newArray, result);// 递归，对后面的子数组组合，并返回以array[i]开头的组合数
			num += subnum;
			// 将组合好的所有新数组中的每个组合放在array[i]后面，构成本层的组合，保存到数组中
			int j = result.length - subnum;// 表示本次组合的结果从哪行开始插入,result.rows表示数组已有行数
			while (j < result.length)
				result[j++][result.length - size] = array[i];// 将头元素加上
		}
		return num;
	}

}
