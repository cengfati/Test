public class MainProgram {

    public static void main (String[] args){
        digitSumRec(12345);
    }

    public static void digitSum(int n){
        int result = 0;

        while(n % 10 > 0){
            result = result + n %10;
            n = n /10;
        }
        System.out.println(result);
    }

    public static int digitSumRec(int n){
        if(n%10 != 0 ){
            System.out.println(n);
            return  n % 10 + digitSumRec(n / 10);
        }else{
            System.out.println("0");
            return 0;
        }
    }

    public static int power(){
        return 0;
    }
}

