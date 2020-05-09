判断两个字符串是否为变形词
public class IsDeforation {
    public boolean isDeforation(String s,String t){
        int[] arr=new int[128];
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            arr[c]++;
        }
        for(int i=0;i<t.length();i++){
            char c=t.charAt(i);
            if(--arr[c]<0) return false;
        }
        return true;
    }
}
是否为旋转词
public class IsRotation {
    public boolean isRotation(String a,String b){
        b+=b;
        return b.contains(a);
    }
}

字符串中数字和
public class NumSum {
    public int numSum(String s){
        int res=0;
        int num=0;
        boolean flag=true;
        for(int i=0;i<s.length();i++){
            int c=s.charAt(i)-'0';
            if(c<0||c>9){
                res+=num;
                num=0;
                if(s.charAt(i)=='-'){
                    if(i>0&&s.charAt(i-1)=='-'){
                        flag=!flag;
                    }else{
                        flag=false;
                    }
                }else{
                    flag=true;
                }
            }else{
                num=num*10+(flag?c:-c);
            }
        }
        return res+num;
    }
}


删除连续k个零
public class RemoveZeros {
    public static String removeZeros(String s,int k){
        s+="#";
        char[] chars=s.toCharArray();
        int start=-1;
        int count=0;
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c=='0'){
                count++;
                start=start==-1?i:start;
            }else{
                if(count==k){
                    while(count--!=0){
                        chars[start++]=0;
                    }
                    count=0;
                    start=-1;
                }
            }
        }
       return new String(chars);
    }
}


替换指定字符
public class Replace {
    public static void main(String[] args) {
        String from="abc";
        String to="123";
        System.out.println(replace("abc2222abcabc",from,to));
    }
    public static String replace(String s,String from,String to){
        int match=0;
        char[] chars=s.toCharArray();
        for(int i=0;i<chars.length;i++){
            if(chars[i]==from.charAt(match)){
                match++;
                if(match==from.length()){
                    clear(chars,i,from.length());
                    match=0;
                }
            }else{
                match=0;
            }
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<chars.length;i++){
            if(chars[i]==0&&(i==0||chars[i-1]!=0)){
                sb.append(to);
            }
            if(chars[i]!=0){
                sb.append(chars[i]);
            }
        }
        return new String(sb);
    }
    private static void clear(char[] chars,int index,int len){
        while(len--!=0){
            chars[index--]=0;
        }
    }
}


字符串的统计字符串
public class GetCountString {
    public static void main(String[] args) {
        System.out.println(getCount("aaabbbdddc"));
    }
    public static String getCount(String s){
        StringBuilder sb=new StringBuilder();
        sb.append(s.charAt(0));
        int count=1;
        for(int i=1;i<s.length();i++){
            char c=s.charAt(i);
            if(c!=s.charAt(i-1)){
                sb.append("_").append(count).append("_").append(c);
                count=1;
            }else{
                count++;
            }
        }
        sb.append("_").append(count);
        return new String(sb);
    }
}


判断所有字符都只出现一次
public class IsUnique {
    //时间复杂度:O(N)
    public boolean isUnique1(String s){
        int[] arr=new int[128];
        for(int i=0;i<s.length();i++){
            if(++arr[s.charAt(i)]>1){
                return false;
            }
        }
        return true;
    }

    //空间复杂度:O(1)    堆排序
    public boolean isUnique2(String s){
        char[] chars=s.toCharArray();
        heapSort(chars);
        for(int i=0;i<chars.length-1;i++){
            if(chars[i]==chars[i+1]) return false;
        }
        return true;
    }
    private void heapSort(char[] chars){
        for(int i=(chars.length-2)/2;i>=0;i--){
            shiftDown(chars,i,chars.length);
        }
        for(int i=0;i<chars.length-1;i++){
            swap(chars,i,chars.length-1-i);
            shiftDown(chars,i,chars.length-i);
        }
    }
    private void shiftDown(char[] chars,int index,int len){
        int left=2*index+1;
        while(left<len){
            int min=left;
            int right=2*index+2;
            if(right<len&&chars[right]<chars[min]){
                min=right;
            }
            if(chars[index]<chars[min]){
                break;
            }
            swap(chars,index,min);
            index=min;
            left=2*index+1;
        }
    }
    private void swap(char[] chars,int left,int right){
        char c=chars[left];
        chars[left]=chars[right];
        chars[right]=c;
    }
}
