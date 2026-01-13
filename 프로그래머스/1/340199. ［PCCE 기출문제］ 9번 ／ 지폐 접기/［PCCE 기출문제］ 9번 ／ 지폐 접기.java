class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        int shortBill = 0;
        int longBill = 0;
        if(bill[0] < bill[1]) {
            shortBill = bill[0];
            longBill = bill[1];
        } else {
            shortBill = bill[1];
            longBill = bill[0];
        }
        
        int shortWallet = 0;
        int longWallet = 0;
        if(wallet[0] < wallet[1]) {
            shortWallet = wallet[0];
            longWallet = wallet[1];
        } else {
            shortWallet = wallet[1];
            longWallet = wallet[0];
        }
        
        while(shortBill > shortWallet || longBill > longWallet) {
            bill = fold(bill);
            if(bill[0] < bill[1]) {
                shortBill = bill[0];
                longBill = bill[1];
            } else {
                shortBill = bill[1];
                longBill = bill[0];
            }
            answer++;
        }
        return answer;
    }
    
    public int[] fold(int[] bill) {
        if(bill[0] > bill[1]) {
            return new int[] {bill[0] / 2, bill[1]};
        }
        return new int[] {bill[0], bill[1] / 2}; 
    }
}
