import "./styles.css";

function main(str) {
  var result = [];
  var prefix = "";
  var answer = -1;

  var permutations = getPermutationsInString(str, prefix, result, str.length);

  for (var i = 0; i < permutations.length; i++) {
    if (isPalindrome(permutations[i]) === 1) {
      answer = 1;
      break;
    }
  }

  return answer;
}

function getPermutationsInString(remainingStr, prefix, result, maxLength) {
  if (remainingStr.length > 2) {
    for (var j = 0; j < remainingStr.length; j++) {
      if (remainingStr.length === maxLength) prefix = "";

      var strArrAux = remainingStr.split("");
      var spliced = strArrAux.splice(j, 1);
      var remainingElements = strArrAux.join("");
      getPermutationsInString(
        remainingElements,
        prefix + spliced,
        result,
        maxLength
      );
    }
  } else {
    result.push(prefix + remainingStr);
    result.push(
      prefix +
        remainingStr
          .split("")
          .reverse()
          .join("")
    );
  }

  return result;
}

function isPalindrome(str) {
  var resp = -1;
  if (
    str ===
    str
      .split("")
      .reverse()
      .join("")
  ) {
    resp = 1;
  } else {
    return -1;
  }
  return resp;
}

document.getElementById("app").innerHTML = "<h1>" + main("111111qqgf") + "</h1>";
