import java.io.*;
import java.util.*;

public class BOJ_2630_suyeon {
	public static int N, whiteColor = 0, blueColor = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		colorPaper paper = new colorPaper(N);	// 초기 색종이 생성
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				paper.setColor(i, j, Integer.parseInt(st.nextToken()));
			}
		}
		
		dfs(paper);
		System.out.println(whiteColor);
		System.out.println(blueColor);
	}
	
	public static class colorPaper {
		public int[][] color;
		
		public colorPaper(int n) {						// 색종이 생성자
			this.color = new int[n][n];
		}
		
		public int getSize() {							// 색종이 가로(세로) 크기 리턴
			return color[0].length;
		}
		
		public int getColor(int y, int x) {				// 색종이 색상 정보 받기
			return color[y][x];
		}
		
		public void setColor(int y, int x, int c) {		// 색종이 색상 정보 저장
			this.color[y][x] = c;
		}
		
		public boolean checkColors() {					// 모든 색이 같은지 판단
			boolean isSame = true;
			int prev = color[0][0];
			for(int i = 0; i < this.getSize(); i++) {
				for(int j = 0; j < this.getSize(); j++) {
					if(color[i][j] != prev) {
						isSame = false;
						return isSame;
					}
					prev = color[i][j];
				}
			}
			return isSame;
		}
		
		public colorPaper cutQuarters(int i) {			// 1번을 넘기면 1번 종이, 2번을 넘기면 2번 종이 "객체"를 리턴하는 메서드
			int newSize = this.getSize() / 2;
			colorPaper p = new colorPaper(newSize);
			switch(i) {
			case 1:		// 1번 색종이(좌상)
				for(int y = 0; y < newSize; y++)
					for(int x = 0; x < newSize; x++)
						p.setColor(y, x, this.getColor(y, x));
				break;
			case 2:		// 2번 색종이(우상)
				for(int y = 0; y < newSize; y++)
					for(int x = 0; x < newSize; x++)
						p.setColor(y, x, this.getColor(y, x+newSize));
				break;
			case 3:		// 3번 색종이(좌하)
				for(int y = 0; y < newSize; y++)
					for(int x = 0; x < newSize; x++)
						p.setColor(y, x, this.getColor(y + newSize, x));
				break;
			case 4:		// 4번 색종이(우하)
				for(int y = 0; y < newSize; y++)
					for(int x = 0; x < newSize; x++)
						p.setColor(y, x, this.getColor(y + newSize, x + newSize));
				break;
			}
			return p;
		}
	}
	
	public static void dfs(colorPaper paper) {
		if(paper.checkColors() == true) {		// 색종이의 색이 모두 같다면 개수 업데이트 후 리턴
			if(paper.getColor(0, 0) == 0) 		whiteColor += 1;
			else if(paper.getColor(0, 0) == 1) 	blueColor += 1;
			return;
		}
		
		// 색종이 색이 모두 같지 않다면
		int quarterSize = paper.getSize();
		colorPaper[] p = new colorPaper[4];				// 4등분한 색종이를 담을 객체 생성
		for(int i = 0; i < 4; i++) {
			p[i] = new colorPaper(quarterSize);			// 4등분 사이즈의 새로운 색종이 객체 생성
			p[i] = paper.cutQuarters(i+1);				// i+1번째의 색종이 정보 입력
			dfs(p[i]);
		}
	}
}
