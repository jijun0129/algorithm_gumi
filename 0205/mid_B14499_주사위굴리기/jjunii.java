package boj;

import java.io.*;
import java.util.*;

public class Boj14499 {
	
	static int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};	// 동서북남
	static int[] dice = new int[6];		// 주사위 각 면의 수
	static int n, m, x, y;
	static int[][]map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < m; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < k; i++) {
			int cmd = Integer.parseInt(st.nextToken());
			
			int result = move(cmd - 1);
			if (result == -1) {
				continue;
			}
			sb.append(result).append('\n');
		}
		System.out.println(sb);
	}
	
	// 주사위 굴리기
	static int move(int d) {
		int nx = x + dir[d][0];
		int ny = y + dir[d][1];

		// 지도 바깥 이동
		if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
			return -1;
		}

		x = nx;
		y = ny;
		changeDice(d);

		if (map[x][y] == 0) {
			map[x][y] = dice[5];
		} else {
			dice[5] = map[x][y];
			map[x][y] = 0;
		}

		return dice[0];
	}
	
	// 주사위 면 숫자 변화
	static void changeDice(int d) {
		int tmp = dice[0];
		switch (d) {
		case 0:	// 동
			dice[0] = dice[3]; dice[3] = dice[5]; dice[5] = dice[2]; dice[2] = tmp;
			break;
		case 1:	// 서
			dice[0] = dice[2]; dice[2] = dice[5]; dice[5] = dice[3]; dice[3] = tmp;
			break;
		case 2:	// 북
			dice[0] = dice[4]; dice[4] = dice[5]; dice[5] = dice[1]; dice[1] = tmp;
			break;
		case 3:	// 남
			dice[0] = dice[1]; dice[1] = dice[5]; dice[5] = dice[4]; dice[4] = tmp;
		}
	}
}
