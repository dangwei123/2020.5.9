在有序但有空元素的数组中查找字符串
public class GetIndex {
    public static void main(String[] args) {
        String[] arr={"null","b","null","c"};
        String target="a";
        System.out.println(getIndex(arr,target));
    }
    public static int getIndex(String[] arr,String target){
        int left=0;
        int right=arr.length-1;
        while(left<right){
            int mid=left+(right-left)/2;
            if(arr[mid].equals("null")){
                int i=mid-1;
                while(i>=left&&arr[i].equals("null")){
                    i--;
                }
                if(i<left||arr[i].compareTo(target)<0){
                    left=mid+1;
                }else{
                    right=mid;
                }

            }else{
                if(arr[mid].compareTo(target)<0){
                    left=mid+1;
                }else{
                    right=mid;
                }
            }
        }
        return arr[left].equals(target)?left:-1;
    }
}


public class Replace2 {
    //将空格替换成%20
    public void replace(char[] chars){
        int len=0;
        int space=0;
        for(int i=0;i<chars.length&&chars[i]!=0;i++){
            if(chars[i]==' ') space++;
            len++;
        }

        int i=len+space*2-1;
        len--;
        while(i>=0){
            if(chars[len]!=' '){
                chars[i--]=chars[len--];
            }else{
                chars[i--]='0';
                chars[i--]='2';
                chars[i--]='%';
            }
        }
    }
    
    //将数字挪到最右侧
    public void copy(char[] chars){
        int index=chars.length-1;
        for(int i=index;i>=0;i--){
            if(chars[i]!='*'){
                chars[index--]=chars[i];
            }
        }
        while(index>=0){
            chars[index--]='*';
        }
    }
}


给你一个字符串 s ，每一次操作你都可以在字符串的任意位置插入任意字符。

请你返回让 s 成为回文串的 最少操作次数 。

「回文串」是正读和反读都相同的字符串。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-insertion-steps-to-make-a-string-palindrome
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class MinInsertions {
    //方法一：
    public int minInsertions1(String s) {
        int n=s.length();
        int[][] dp=new int[n+1][n+1];
        for(int i=1;i<=n;i++){
            for(int j=n;j>0;j--){
                if(s.charAt(i-1)==s.charAt(j-1)){
                    dp[i][n-j+1]=dp[i-1][n-j]+1;
                }else{
                    dp[i][n-j+1]=Math.max(dp[i-1][n-j+1],dp[i][n-j]);
                }
            }
        }
        return n-dp[n][n];
    }

    //方法二：
    public int minInsertions2(String s) {
        int n=s.length();
        int[][] dp=new int[n][n];

        for(int j=1;j<n;j++){
            dp[j-1][j]=s.charAt(j-1)==s.charAt(j)?0:1;
            for(int i=j-2;i>=0;i--){
                if(s.charAt(i)==s.charAt(j)){
                    dp[i][j]=dp[i+1][j-1];
                }else{
                    dp[i][j]=Math.min(dp[i+1][j],dp[i][j-1])+1;
                }
            }
        }
        return dp[0][n-1];
    }

    //恢复
    public String rebuild(String s,int[][] dp){
        int n=s.length()+dp[0][s.length()-1];
        char[] res=new char[n];
        int left=0;
        int right=n-1;
        int i=0;
        int j=s.length()-1;
        while(i<=j){
            if(s.charAt(i)==s.charAt(j)){
                res[left++]=s.charAt(i++);
                res[right--]=s.charAt(j--);
            }else if(dp[i+1][j]<dp[i][j-1]){
                res[left++]=s.charAt(i);
                res[right--]=s.charAt(i++);
            }else{
                res[left++]=s.charAt(j);
                res[right]=s.charAt(j--);
            }
        }
        return new String(res);
    }
}


 括号的有效性和最长有效长度
 public class IsValid {
    public boolean isValid(String s){
        int count=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='(') count++;
            else if(s.charAt(i)==')') count--;
            else return false;

            if(count<0) return false;
        }
        return true;
    }

    public int maxLen(String s){
        int n=s.length();
        int[] dp=new int[n];
        int res=0;
        for(int i=1;i<n;i++){
            if(s.charAt(i)==')'){
                int pre=i-dp[i-1]-1;
                if(pre>=0&&s.charAt(pre)=='('){
                    dp[i]=dp[i-1]+2+(pre>0?dp[pre-1]:0);
                    res=Math.max(res,dp[i]);
                }
            }
        }
        return res;
    }
}

0左边必有1的字符串数量(斐波那契数列)
public class GetNum {
    public int getNum1(int n){
        return get(0,n);
    }
    private int get(int i,int n){
        if(i==n-1) return 2;
        if(i==n) return 1;
        return get(i+1,n)+get(i+2,n);
    }
}


拼接所有字符串中在字典顺序中最小
public class LowestString {
    public String lowestString(String[] arr){
        Arrays.sort(arr,(a,b)-> (a+b).compareTo(b+a));
        StringBuilder sb=new StringBuilder();
        for(String s:arr){
            sb.append(s);
        }
        return new String(sb);
    }
}

给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
public int lengthOfLongestSubstring(String s) {
        int[] windows=new int[128];
        int max=0;
        int left=0;
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            windows[c]++;
            while(windows[c]>1){
                windows[s.charAt(left)]--;
                left++;
            }
            max=Math.max(max,i-left+1);
        }
        return max;
    }
	
	给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串
	public String minWindow(String s, String t) {
        int[] needs=new int[128];
        int[] windows=new int[128];
        for(int i=0;i<t.length();i++){
            needs[t.charAt(i)]++;
        }
        int min=s.length()+1;
        int i=0;
        int count=0;
        int left=0;
        String res="";
        while(i<s.length()){
            char c=s.charAt(i);
            windows[c]++;
            if(windows[c]<=needs[c]){
                count++;
            }
            i++;

            while(count==t.length()){
                if(i-left<min){
                    min=i-left;
                    res=s.substring(left,i);
                }
                char ch=s.charAt(left);
                if(windows[ch]<=needs[ch]){
                    count--;
                }
                windows[ch]--;
                left++;
            }
        }
        return res;
    }
	
	给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。

返回符合要求的最少分割次数。
public int minCut(String s) {
        int n=s.length();
        int[] dp=new int[n];
        boolean[][] isPalid=new boolean[n][n];
        for(int i=0;i<n;i++){
            dp[i]=n;
            for(int j=0;j<=i;j++){
                if((s.charAt(i)==s.charAt(j))&&(i-j<=1||isPalid[j+1][i-1])){
                    dp[i]=j==0?0:Math.min(dp[i],dp[j-1]+1);
                    isPalid[j][i]=true;
                }
            }
        }
        return dp[n-1];
    }
	
	匹配问题
	public class IsMatchedProblems {
    //正则表达式
    public boolean isMatch(String s, String p) {
        int m=s.length();
        int n=p.length();
        boolean[][] dp=new boolean[m+1][n+1];
        dp[0][0]=true;
        for(int i=2;i<=n;i++){
            dp[0][i]=(p.charAt(i-1)=='*')&&dp[0][i-2];
        }
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(p.charAt(j-1)=='*'){
                    dp[i][j]=(dp[i][j-2])||(isMatched(s,p,i-1,j-2)&&dp[i-1][j]);
                }else{
                    dp[i][j]=dp[i-1][j-1]&&isMatched(s,p,i-1,j-1);
                }
            }
        }
        return dp[m][n];
    }
    private boolean isMatched(String s,String p,int i,int j){
        return s.charAt(i)==p.charAt(j)||p.charAt(j)=='.';
    }


    //通配符
    public boolean isMatch2(String s, String p) {
        int m=s.length();
        int n=p.length();
        boolean[][] dp=new boolean[m+1][n+1];
        dp[0][0]=true;
        for(int i=1;i<=n;i++){
            dp[0][i]=p.charAt(i-1)=='*'&&dp[0][i-1];
        }
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(s.charAt(i-1)==p.charAt(j-1)||p.charAt(j-1)=='?'){
                    dp[i][j]=dp[i-1][j-1];
                }else if(p.charAt(j-1)=='*'){
                    dp[i][j]=dp[i-1][j]||dp[i][j-1];
                }
            }
        }
        return dp[m][n];
    }
}
