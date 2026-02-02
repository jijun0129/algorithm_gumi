import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1316_minjun {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int cnt =0;
		for (int i=0; i<n;i++) {
			// 그룹 단어 개수 구하기
			// visited 체크?
			// 알파벳 소문자만 저장하는 배열
			// 처음 문자가 들어오면 해당 문자 visited 체크
			// 다음게 이전과 같으면? 여전히 그룹단어
			// 다음게 이전과 다르면 있는지 확인필요
			// 있으면 그룹 단어 아님
			
			String input = br.readLine();
			int[] visited = new int[26];
			Arrays.fill(visited, -1);
			char prev = ' ';
			boolean isGroup = true;
			
			for (int j=0; j<input.length();j++) {
				char crtChar = input.charAt(j);
				int crtAbc = (int)crtChar-'a';
				
				if(visited[crtAbc] == -1) { // 방문 안했으면?
					visited[crtAbc]++;
				} else if (visited[crtAbc] != -1 && prev != crtChar) { // 방문 했는데 이전과 다르면
					isGroup = false;
					break;
				} 
				prev = input.charAt(j);
			}
			
			if(isGroup) { // flag 체크해서 cnt 증가시키기
				cnt++;
			}
		}
		System.out.println(cnt);
	}

}
