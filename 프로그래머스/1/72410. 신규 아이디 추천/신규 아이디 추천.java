class Solution {
    // 1. tolowercase()
    // 2. 알파벳, 숫자, -, _, .를 제외한 모든 문자 제거
    // 3. 마침표가 2개 이상이면 1개로
    // 4. 마침표가 처음이나 끝에 위치하면 제거
    // 5. "" -> "a"
    // 6. 글자가 16자 이상 -> substring(0, 16);
    // 7. 2자 이하라면 마지막 문자를 repeat()
    public String solution(String new_id) {
        String id = new_id;
        
        id = id.toLowerCase();
        id = deleteInvalidLetter(id);

        while(id.contains("..")) {
            id = id.replace("..", ".");
        }
        
        if(id.charAt(0) == '.') {
            id = id.substring(1);
        }
        
        if(id.length() > 0 && id.charAt(id.length() - 1) == '.') {
            id = id.substring(0, id.length() - 1);
        }
        
        if(id.equals("")) {
            id = "a";
        }
        
        if(id.length() >= 16) {
            id = id.substring(0, 15);
            if(id.length() > 0 && id.charAt(id.length() - 1) == '.') {
                id = id.substring(0, id.length() - 1);
            }
        }
        
        while(id.length() <= 2) {
            id = id + id.charAt(id.length() - 1);
        }
        
        return id;
    }
    
    public String deleteInvalidLetter(String id) {
        String[] validSpecialLetters = {"-", "_", "."};
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < id.length() ; i++) {
            char currentLetter = id.charAt(i);
            if(currentLetter >= 'a' && currentLetter <= 'z') {
                sb.append(currentLetter);
                continue;
            }
            
            if(currentLetter >= '0' && currentLetter <= '9')  {
                sb.append(currentLetter);
                continue;
            }
            
            for(int j = 0 ; j < validSpecialLetters.length ; j++) {
                if(String.valueOf(currentLetter).equals(validSpecialLetters[j])) {
                    sb.append(currentLetter);
                    break;
                }
            }
        }
        
        return sb.toString();
    }
}