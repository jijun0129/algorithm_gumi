package algorithmStudy;
import java.util.*;
import java.io.*;

// 	첫번째 접근: 그리디
//	그리디로 해 도출 불가
//	-> 그리디는 당장의 눈 앞에 있는 가장 최선의 선택을 기준으로 해를 찾아감.
//	이 문제는 모듈러 연산과 L, R이 있어 목표하는 값을 선형적으로 맞추어갈 수 없음. 비선형적임
//	만약 모듈러 연산과 L, R 없이 *2, -1 연산을 가지고 목표하는 값을 찾아가는 것이라면 그리디로 해 도출 가능
//
//	두번째 접근: BFS
//	이동하는 비용이 항상 1이므로 수직선 상에서 시작값부터 목표값까지 도달하는 최단 경로 찾기
//	
//	1. 정의
//	시작점 A에서 K번의 연산으로 도달할 수 있는 수들의 집합을 step[K]라 하자.

//	2. 탐색 과정
//	- step[0]은 시작점 A 하나뿐이다.
//	- step[K-1]에 있는 모든 수에 대해 D, S, L, R 연산을 수행한다.
//	- 이때 나온 결과값 중 "지금까지 한 번도 방문하지 않은 수"만 step[K]에 추가한다.

//	3. 최단 거리 보장 이유
//	BFS는 주변부터 순차적으로 넓게 탐색한다.
//	만약 목표값 B가 step[K]에서 처음 발견되었다면, K가 곧 최단 경로이다.
//	왜냐하면 K보다 더 적은 횟수(1 ~ K-1)로 도달할 수 있는 경로가 존재했다면,
//	앞선 단계의 탐색에서 이미 발견되어 방문 처리(visited)가 되었을 것.
//	따라서 처음 발견된 순간의 횟수가 최소 연산 횟수임이 보장된다.

public class boj9019 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st;
	public static StringBuilder sb = new StringBuilder();
	public static final int MOD = 10000;
	
	public static int nextInt() throws IOException {
		if(st == null || !st.hasMoreElements()) {
			st = new StringTokenizer(br.readLine());
		}
		return Integer.parseInt(st.nextToken());
	}
	
	
	public static void main(String[] args) throws IOException {
		int T = nextInt();
		
		while(T-- > 0) {
			int n = nextInt();
			int t = nextInt();
			sb.append(bfs(n,t)).append("\n");
		}
		System.out.println(sb);
	}
	
	static String bfs(int st, int en) {
		boolean[] visited = new boolean[MOD];
		int[] p = new int[MOD];
		char[] cmd = new char[MOD];
		
		Queue<Integer> q = new LinkedList<>();
		q.add(st);
		visited[st] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			if(cur == en) break;
			
			int next = (cur << 1) % MOD;
			if(!visited[next]) {
				q.add(next);
				visited[next] = true;
				p[next] = cur;
				cmd[next] = 'D';
			}
			
			next = (cur == 0) ? MOD-1 : cur-1;
			if(!visited[next]) {
				q.add(next);
				visited[next] = true;
				p[next] = cur;
				cmd[next] = 'S';
			}
			next = (cur % 1000) * 10 + cur / 1000;
			if(!visited[next]) {
				q.add(next);
				visited[next] = true;
				p[next] = cur;
				cmd[next] = 'L';
			}
			next = (cur % 10) * 1000 + cur / 10;
			if(!visited[next]) {
				q.add(next);
				visited[next] = true;
				p[next] = cur;
				cmd[next] = 'R';
			}
		}
		
		StringBuilder path = new StringBuilder();
		int temp = en;
		while(temp != st) {
			path.append(cmd[temp]);
			temp = p[temp];
		}
		return path.reverse().toString();
	}
}
