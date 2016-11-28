import java.util.regex.*;
import java.util.*;
/**
 * 
 */
public class Expression {

    /**
     * Default constructor
     */
	
    public Expression() {
    }

    /**
     * 
     */
    private String expContent;
    private String resContent;
	private static Pattern simSplit = Pattern.compile("[\\s=]+");
	private static Pattern plusSplit = Pattern.compile("[+]");
	private static Pattern mulSplit = Pattern.compile("[*]");
	private static Pattern bracketsPattern = Pattern.compile("([a-zA-Z0-9]+\\*)*\\([a-zA-Z0-9*+-]+\\){1}(\\*[a-zA-Z0-9]+)*");
	private static Pattern naiveBrPattern = Pattern.compile("\\([a-zA-Z0-9*+-^]+\\)");
	private static Pattern specBrPattern = Pattern.compile("\\)\\*\\(");
	
	private static String[] splitByPlus;
	private static String[] splitByMul;
	
    public String simplify(Command command) {
        // TODO implement here
		String simplifiedExp = expContent;
		String cmd = command.getContent();
		String[] strs = simSplit.split(cmd);
		for (int i = 1; i < strs.length;) {
			String pre = simplifiedExp;
			if ((simplifiedExp = simplifiedExp.replaceAll(strs[i++], strs[i++])) == pre) {
				resContent = "Existing unavailable syntax." ;
				return resContent;
			}
		}
		String ret= addPower(merge(simplifiedExp));
		resContent=ret;
		return resContent;
    }

    /**
     * 
     */
    public String derivative(Command command) {
        // TODO implement here
    	String cmd = command.getContent();
    	String exp = expContent;
    	String tarVar = cmd.substring(5);
		String derivedExp = "";
		splitByPlus = plusSplit.split(exp);

		if (exp.indexOf(tarVar) == -1) {
			resContent = "Error! No variable!";
			return resContent;
		}
		for (int i = 0; i < splitByPlus.length; i++) {
			String tempStr = "";
			int cnt = countPower(splitByPlus[i], tarVar);
			if (cnt == 0) {
				tempStr = "0";
			} else {
				tempStr = splitByPlus[i];
				int powerOfTarVar = countPower(tempStr, tarVar);
				tempStr = tempStr.replaceFirst(tarVar, String.valueOf(powerOfTarVar));
			}
			if (i == 0) {
				derivedExp = tempStr;
			} else {
				derivedExp = derivedExp + "+" + tempStr;
			}
		}
		String showExp = addPower(merge(derivedExp));
		resContent = showExp;
		return resContent;
    }


    /**
     * 
     */
    public void setExpContent(String str) {
        // TODO implement here
        expContent = str;
    }

    /**
     * 
     */
    public void setResContent(String str) {
        // TODO implement here
        resContent = str;
    }

    /**
     * 
     */

    public String getExpContent() {
        // TODO implement here
        return this.expContent;
    }
    
    public String getResContent() {
        // TODO implement here
        return this.resContent;
    }
    
    /**
     * 
     */
    
    
    
    private String combine(String[] strs,int [] num,int strsLen) {
        // TODO implement here
		String [] hash = new String[1000];
		int [] cnt = new int [1000];
		int [] pos = new int [1000];
		int p = 0;
		for (int i = 0; i < strsLen; i++) {
			int x;
			Integer itemp = strs[i].hashCode();
				if (itemp !=  Integer.MIN_VALUE) {
					x = Math.abs(itemp % 953);
				} else {
					x = Math.abs(Integer.MIN_VALUE % 953);
				}
			//int x=Math.abs(strs[i].hashCode())%953;
			while (hash[x] != null) {
				if (hash[x].equals(strs[i])) {
					break;
					} else {
					x += 3;
					}
			}
			if (hash[x] == null) {
				hash[x] = strs[i];
				cnt[x] = num[i];
				pos[p] = x;
				p++;
			} else {
			cnt[x] += num[i];
			}
		}

		String ret = "";
		for (int i = 0; i < p; i++) {
			if (cnt[pos[i]] == 1) {
				ret = ret + "+" + hash[pos[i]];
			} else {
				ret = ret + "+" + String.valueOf(cnt[pos[i]]) + "*" + hash[pos[i]];
			}
		}
		return ret;

    }

    /**
     * 
     */
    private String getCommon(String exp) {
		if (exp.indexOf("+") == -1) {
			return exp;
		}
		String resultStr = "";
		String[] charMul = new String[1000];
		String[] digitMul = new String[1000];
		int charMulCnt = 0;
		int digitMulCnt = 0;
		String offsetHAHAHA = "0";

		Pattern patternDigitPlus = Pattern.compile("[0-9]+\\+");
		Matcher digitPlusMatcher = patternDigitPlus.matcher(exp);
		if (digitPlusMatcher.find()) {
			offsetHAHAHA = digitPlusMatcher.group(0);
			offsetHAHAHA = offsetHAHAHA.substring(0, offsetHAHAHA.length() - 1);
			exp = exp.replace(digitPlusMatcher.group(0), "");
		}
		Pattern digitPattern = Pattern.compile("[0-9]+");
		Pattern pattern = Pattern.compile("([a-zA-Z]+\\*)*[a-zA-Z]+");
//		Matcher matcher = Matcher.matches(exp);
		splitByPlus = plusSplit.split(exp);
		for (int i = 0; i < splitByPlus.length; i++) {
			String mulStr = splitByPlus[i];
			Matcher digitMatcher = digitPattern.matcher(mulStr);
			Matcher charMatcher = pattern.matcher(mulStr);
			if (digitMatcher.find()) {
				digitMul[digitMulCnt++] = digitMatcher.group(0);
			} else {
				digitMul[digitMulCnt++] = "1";
			}
			if (charMatcher.find()) {
				charMul[charMulCnt++] = charMatcher.group(0);
			}
		}
		int[] nums = new int[1000];

		for (int i = 0; i < digitMulCnt; i++) {
			nums[i] = Integer.parseInt(digitMul[i]);
		}
		resultStr = combine(charMul, nums, digitMulCnt);
		if (offsetHAHAHA != "0"){
			resultStr = offsetHAHAHA + resultStr;
		} else {
			resultStr = resultStr.substring(1);
		}
		return resultStr;

    }

    
    
    private String merge(String simplifiedExp){
		String outAns = "";
		splitByPlus = plusSplit.split(simplifiedExp);
		for (int i = 0; i < splitByPlus.length; i++) {
			splitByMul = mulSplit.split(splitByPlus[i]);
			Arrays.sort(splitByMul);
			long mulsAns = 1;
			boolean flag = false;
			boolean flagVar = false;
			String outTmp = "";
			for (int j = 0; j < splitByMul.length; j++) {
				if (isNumeric(splitByMul[j])) {
					mulsAns = mulsAns * Integer.parseInt(splitByMul[j]);
					flag = true;
				} else {
					flagVar = true;
					outTmp = outTmp + "*" + splitByMul[j];
				}
			}
			if (mulsAns == 1 && !flag || mulsAns == 1 && flag && flagVar) {
				outTmp = outTmp.substring(1, outTmp.length());
			}
			else if (mulsAns == 0) {
				outTmp = "";
			}
			else{
				outTmp = String.valueOf(mulsAns) + outTmp;
			}
			if (mulsAns != 0) {
			outAns = outAns + "+" + outTmp;
			}
		}
		if (outAns.length() == 0) {
			outAns = "0";
			} else{
		outAns = outAns.substring(1, outAns.length());
		}

		String[] plusAnsSplit = plusSplit.split(outAns);

		int plusAns = 0;
		boolean flagVar = false;
		outAns = "";
		for (int i = 0;i < plusAnsSplit.length; i++){
			if (isNumeric(plusAnsSplit[i])) {
				plusAns = plusAns + Integer.parseInt(plusAnsSplit[i]);
			} else {
				flagVar = true;
				outAns = outAns + "+" + plusAnsSplit[i];
			}
		}
		if (plusAns != 0) {
			outAns = String.valueOf(plusAns) + outAns;
		} else if (plusAns == 0 && flagVar) {
			outAns = outAns.substring(1, outAns.length());
		} else if (!flagVar) {
			outAns = String.valueOf(plusAns);
		}
		outAns = getCommon(outAns);
		return outAns;
    }

    /**
     * 
     */
    private Boolean isNumeric(String str) {
        // TODO implement here
        for (int i = 0; i < str.length(); i++)
        if (!Character.isDigit(str.charAt(i))) {
            return false;
            }
        return true;
    }

    /**
     * 
     */
    private String digitCharSplit(String exp) {
		Pattern pattern = Pattern.compile("[0-9]+[a-zA-Z]+");
		Pattern numericPattern = Pattern.compile("[0-9]+");
		Matcher matcher  = pattern.matcher(exp);
		Matcher numericMatcher;
		while (matcher.find()) {
			String tempStr = matcher.group(0);

			numericMatcher = numericPattern.matcher(tempStr);
			if (numericMatcher.find()) {
				String charStr = tempStr;
				String numericStr = numericMatcher.group(0);
				charStr = charStr.replace(numericStr, "");
				String resultStr = numericStr + "*" + charStr;
				exp = exp.replace(tempStr, resultStr);
				}
		}
		return exp;
    }

    /**
     * 
     */
    private String specBrPower(String exp) {
        // TODO implement here
		String inBr = "";
		String res = "";
		String totBrPower;
		String powerStr;
		int powerCnt = 0;
		int	leftStack = 1;
		int	rightIndex = 0;
		int leftIndex = 0;
		Pattern specBrPattern = Pattern.compile("\\)\\^");
		int index = exp.indexOf(")^");
		rightIndex = index + 2;
		leftIndex = index - 1;
		leftStack = 1;
		while (rightIndex < exp.length() && Character.isDigit(exp.charAt(rightIndex))) {
			rightIndex++;
		}
		while (leftIndex >= 0 && leftStack != 0) {
			if (exp.charAt(leftIndex) == ')') {
				leftStack++;
			}
			if (exp.charAt(leftIndex) == '(') {
				leftStack--;
			}
			leftIndex--;
		}
		totBrPower = exp.substring(leftIndex + 1, rightIndex);
		index = totBrPower.indexOf(")^");
		powerStr = totBrPower.substring(index + 2, totBrPower.length());
		inBr = totBrPower.substring(0, index + 1);
		powerCnt = Integer.parseInt(powerStr);

		for (int i = 0; i < powerCnt - 1; i++) {
			res += inBr + "*";
		}
		res += inBr;
		exp = exp.replace(totBrPower, res);
		if (specBrPattern.matcher(exp).find()) {
			exp = specBrPower(exp);
		}
		return exp;
    }

    /**
     * 
     */
    private String dealIndex(String exp) {
		Pattern pattern = Pattern.compile("[a-zA-Z]+[\\s]*\\^[\\s]*[0-9]+");
		Matcher matcher  = pattern.matcher(exp);
		Pattern specPattern = Pattern.compile("\\)\\^");
		Matcher specMatcher = specPattern.matcher(exp);

		if (specMatcher.find()) {
			exp = specBrPower(exp);
		}
		while (matcher.find()) {
			String tempStr = matcher.group(0);
			String[] result = tempStr.split("[\\s]*[\\^]+[\\s]*");
			int p = Integer.parseInt(result[1]);
			String rep;
			rep = result[0];
			for (int i = 1; i < p; i++) {
				rep = rep + '*' + result[0];
			}
			exp = exp.replace(tempStr, rep);
		}
		return exp;
    }

    /**
     * 
     */
    private String fraction(String para, String inBrackets) {
		String resultStr = "";
		String[] elements = plusSplit.split(inBrackets);
		for (int i = 0; i < elements.length - 1; i++) {
			resultStr += para + "*" + elements[i] + "+";
		}
		resultStr += para + "*" + elements[elements.length - 1];
		return resultStr;
    }

    /**
     * 
     */
    private String deleteBrackets(String exp) {
		String resultStr = "";
		Matcher naiveBrMatcher = naiveBrPattern.matcher(exp);
		naiveBrMatcher.find();
		resultStr = naiveBrMatcher.group(0);
		exp = exp.replaceAll("\\([a-zA-Z0-9*+-^]+\\)", "1");
		exp = merge(exp);

		resultStr = merge(resultStr.substring(1, resultStr.length() - 1));
		resultStr = merge(fraction(exp, resultStr));
		return resultStr;
    }

    /**
     * 
     */
    private String mergeBrackets(String exp) {
		String resultStr = "";
		while (true) {
			Matcher bracketsMatcher = bracketsPattern.matcher(exp);
			if (bracketsMatcher.find()) {
				exp = exp.replace(bracketsMatcher.group(0),
				deleteBrackets(bracketsMatcher.group(0)));
			} else {
				break;
			}
		}
		resultStr = exp;
		return resultStr;
    }

    /**
     * 
     */
    private String mergeSpecBrackets(String exp) {
		String resultStr = "";
		int index = exp.indexOf(")*(");

		int ltIt = index - 1;
		int	rtIt = index + 3;
		int leftStack = 1;
		int	rightStack = 1;
		while (ltIt >= 0 && leftStack != 0) {
			if (exp.charAt(ltIt) == '(') {
				leftStack--;
			} else if (exp.charAt(ltIt) == ')') {
				leftStack++;
			}
			ltIt--;
		}

		while (rtIt < exp.length() && rightStack != 0) {
			if (exp.charAt(rtIt) == '(') {
				rightStack++;
			} else if (exp.charAt(rtIt) == ')') {
				rightStack--;
			}
			rtIt++;
		}

		String handleExp = exp.substring(ltIt + 1, rtIt);
		String firstBr;
		String secondBr;
		String[] firstSplit = new String[1000];
		int index2 = handleExp.indexOf(")*(");
		firstBr = handleExp.substring(1, index2);
		secondBr = handleExp.substring(index2 + 2);

		String firstRes = "";
		rightStack = 0;
		int plusIndex = 0;
		int	preIndex = 0;
		int firstSplitCnt = 0;
		String tempFirstBr = firstBr;

		for (int i = 0; i < tempFirstBr.length(); i++) {
			if (rightStack == 0 && tempFirstBr.charAt(i) == '+') {
				firstSplit[firstSplitCnt++] = tempFirstBr.substring(0, i);
				tempFirstBr = tempFirstBr.substring(i + 1); // after '+';
				i = 0;
				rightStack = 0;
				continue;
			}
			if (firstBr.charAt(i) == '(') {
				rightStack++;
			}
			if (firstBr.charAt(i) == ')') {
				rightStack--;
			}
		}

		firstSplit[firstSplitCnt++] = tempFirstBr.substring(0, tempFirstBr.length());


		for (int i = 0; i < firstSplitCnt - 1; i++) {
			firstRes += firstSplit[i] + "*" + secondBr + "+";
		}
		firstRes += firstSplit[firstSplitCnt - 1] + "*" + secondBr;
		firstRes = "(" + firstRes + ")";
		exp = exp.replace(handleExp, firstRes);

		if (specBrPattern.matcher(exp).find()) {
			exp = mergeSpecBrackets(exp);
		} else if (naiveBrPattern.matcher(exp).find()) {
			exp = mergeBrackets(exp);
		}
		return exp;

    }

    /**
     * 
     */
    private int countPower(String expFrag, String tarVar) {
		int count = 0;
		int index = 0;

		String pStr = tarVar.replace("*", "\\*");
		pStr = "[^a-zA-Z*]*" + pStr + "[^a-zA-Z*]*";
		Pattern p = Pattern.compile(pStr);
		Matcher m = p.matcher(expFrag);
		while (m.find()) {
			count++;
		}
		return count;
    }

    /**
     * 
     */
     String addPower(String originexp) {
		String exp = originexp;
		String derivedExp = "";
		splitByPlus = plusSplit.split(exp);

		for (int i = 0; i < splitByPlus.length; i++) {
			String tempStr = splitByPlus[i];
			String tempVar = "";
			splitByMul = mulSplit.split(splitByPlus[i]);
			for (int j = splitByMul.length - 1; j >= 0; j--) {
				String tarVar = splitByMul[j];

				if (tempVar.equals(tarVar)) {
					continue;
				}
				int cnt = countPower(splitByPlus[i], tarVar);
				if (cnt > 1) {
					Pattern pattern = Pattern.compile("(" + tarVar + "\\*)*" + tarVar);
					Matcher matcher = pattern.matcher(tempStr);
					String patternStr = "";
					if (matcher.find()) {
						patternStr = matcher.group(0);
						patternStr = patternStr.replace("*", "\\*");
					}
					tempStr = tempStr.replaceAll("[^a-zA-Z*]*" + patternStr + "[^a-zA-Z*]*", tarVar + "^" + String.valueOf(cnt));
					tempVar = tarVar;
				}
			}
			exp = exp.replace(splitByPlus[i], tempStr);
		}
		return exp;     
    }

    /**
     * 
     */
    public String init(String exp) {
		if (exp.isEmpty()) {
			return "";
		}
		if (ifWrong(exp) == -1) {
			return "";
		}
		exp = digitCharSplit(exp);
		exp = dealIndex(exp);
		if (specBrPattern.matcher(exp).find()) {
			exp = mergeSpecBrackets(exp);
		} else if (naiveBrPattern.matcher(exp).find()) {
			exp = mergeBrackets(exp);
		}
		exp = merge(exp);

		if (exp.isEmpty()) {
			resContent = "Error!";
		} else {
			String showExp = addPower(exp);
			resContent = showExp;
		}

		return exp;
    }

    /**
     * 
     */
    private int ifWrong(String exp) {
		Pattern pattern0 = Pattern.compile("[^a-zA-Z0-9+*()\\^\\s]");
		Pattern pattern1 = Pattern.compile("\\)");
		Pattern pattern2 = Pattern.compile("\\(");
		Pattern pattern3 = Pattern.compile("\\(\\s*\\)");
		Pattern pattern4 = Pattern.compile("[^a-zA-Z0-9\\)]+\\+");
		Pattern pattern5 = Pattern.compile("[^a-zA-Z0-9\\)]+\\*");
		Pattern pattern6 = Pattern.compile("[^a-zA-Z\\)]+\\^");
		Pattern pattern7 = Pattern.compile("[^\\+\\*\\(]+\\(");
		Pattern pattern8 = Pattern.compile("[^a-zA-Z0-9\\)]+\\)");
		Pattern pattern88 = Pattern.compile("^[^0-9a-zA-Z\\(]+");
		Pattern pattern99 = Pattern.compile("[^0-9a-zA-Z\\)]+$");

		Matcher matcher0 = pattern0.matcher(exp);
		Matcher matcher1 = pattern1.matcher(exp);
		Matcher matcher2 = pattern2.matcher(exp);
		Matcher matcher3 = pattern3.matcher(exp);
		Matcher matcher4 = pattern4.matcher(exp);
		Matcher matcher5 = pattern5.matcher(exp);
		Matcher matcher6 = pattern6.matcher(exp);
		Matcher matcher7 = pattern7.matcher(exp);
		Matcher matcher8 = pattern8.matcher(exp);
		Matcher matcher88 = pattern88.matcher(exp);
		Matcher matcher99 = pattern99.matcher(exp);

		if (matcher0.find()) {
			resContent = "Illegal character(s)!" ;
			return -1;
		}
		int leftCnt = 0;
		int	rightCnt = 0;
		while (matcher1.find()) {
			rightCnt++;
		}
		while (matcher2.find()) {
			leftCnt++;
		}
		if (leftCnt != rightCnt) {
			resContent = "No matched brackets pattern.";
			return -1;
		}
		if (exp.indexOf("(") > exp.indexOf(")")) {
			resContent = "No matched brackets pattern.";
			return -1;
		}
		if (matcher4.find() || matcher5.find() || matcher6.find() || matcher7.find() || matcher8.find()) {
			resContent = "No correct expressions.";
			return -1;
		}
		if (matcher88.find() || matcher99.find()) {
			resContent = "No correct expressions.";
			return -1;
		}
		return 0;
    }
    	

}