package project3;

import java.security.cert.TrustAnchor;
import java.util.Comparator;

/**
 * The Number class is used to represent positive integers. It provides several operations to support number
 * manipulation: addition, multiplication and comparisons.
 *
 * There is no limit to the number of digits in a Number object.
 *
 * The numbers are represented in the form of a linked list. Each node in the list contains a single digit.
 * For example, * 134 is represented as a three node list:
 *
 *  head --> | 4 | --> | 3 | --> | 1 | --> null
 *
 * and 98765 is represented as a five node list:
 *  head --> | 5 | --> | 6 | --> | 7 | --> | 8 | --> | 9 | --> null
 *
 * The least significant digit, or the ones digit, is stored in the very first node. The tens digit is stored
 * in the second node. And so on ....
 *
 * @author William Wang
 */

public class Number implements Comparable<Number>{

    // Pointer pointing to the beginning and the end of the list
    private Node head = null;
    private Node tail = null;

    /**
     * A Number object with value represented by the string argument number. The number should consist of decimal digits only.
     * @param token a string representation of the number
     * @throws IllegalArgumentException if number is null
     * @throws NullPointerException if number contains any illegal characters
     */
    public Number(String token) throws IllegalArgumentException,NullPointerException {
        if(token == null) throw new NullPointerException("The input is null");
        char [] Chars = token.toCharArray();
        if(Chars.length>0){
            if(!Character.isDigit(Chars[Chars.length-1])) throw new IllegalArgumentException("Non digit in the input");
            int h = Character.getNumericValue(Chars[Chars.length-1]);
            head = new Node(h);
            tail = head;
            for(int i = Chars.length-2;i>=0;i--){
                if(!Character.isDigit(Chars[i])) throw new IllegalArgumentException("Non digit in the input");
                int D = Character.getNumericValue(Chars[i]);
                Node current = new Node(D);
                tail.next = current;
                tail = current;
            }
        }
    }

    /**
     * Returns the number of digits in this object.
     * @return the number of digits in this object
     */
    public int length(){
        int len = 0;
        Node current = this.head;
        while (current!=null){
            len+=1;
            current = current.next;
        }
        return len;
    }

    // A node class containing a int as data, constructing a linked list.
    private class Node {
        public int data;
        public Node next;
        public Node(int data){
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Computes the sum of this object with other. Returns the result in a new object.
     * This object is not modified by call to add.
     * @param other the value to be added to this object
     * @return a Number object whose value is equal to the sum of this object and other
     * @throws NullPointerException if other is null
     */
    public Number add(Number other) throws NullPointerException{
        if(other == null) throw new NullPointerException("Can't be adding to Null");

        Node currentThis = this.head;
        Node currentOther = other.head;
        int carryOver = 0;
        int digit;
        String rec = "";

        //Add by digits
        while(currentOther!=null && currentThis!=null){
            digit = currentOther.data + currentThis.data + carryOver;
            if(digit>=10){
                carryOver = 1;
                digit=digit%10;
            }
            else {
                carryOver = 0;
            }
            rec = digit + rec;
            currentOther = currentOther.next;
            currentThis = currentThis.next;
        }

        //Add on the remaining digit
        Node remain=null;
        if(currentOther == null) remain = currentThis;
        if(currentThis == null) remain = currentOther;
        if(remain!=null){
            while (remain!=null){
                digit = remain.data + carryOver;
                if(digit>=10){
                    carryOver = 1;
                    digit=digit%10;
                }
                else {
                    carryOver = 0;
                }
                rec = digit + rec;
                remain = remain.next;
            }
        }
        if(carryOver!=0) rec = carryOver + rec;

        return new Number(rec);
    }

    /**
     * Computes the product of this object and a single digit digit.
     * Returns the result in a new object. This object is not modified by call to multiplyByDigit.
     * @param digit a single positive digit to be used for multiplication
     * @return a Number object whose value is equal to the product of this object and digit
     * @throws IllegalArgumentException when digit is invalid (i.e., not a single digit or negative)
     */
    public Number multiplyByDigit(int digit) throws IllegalArgumentException{
        if(digit>=10 || digit<0) throw new IllegalArgumentException
                ("Input should be a positive one digit int");

        int carryOver = 0;
        Node currentOther = this.head;
        String out = "";
        int num;

        //Multiply by digit
        while (currentOther!=null){
            num = currentOther.data * digit + carryOver;
            carryOver = num/10;
            num = num%10;
            out = num + out;
            currentOther = currentOther.next;
        }
        if(carryOver!=0) out = carryOver + out;
        return new Number(out);
    }

    /**
     * Computes the product of this object and other.
     * Returns the result in a new object. This object is not modified by call to multiply.
     * @param other the value to be multiplied by this object
     * @return the result in a Number object which is the produce of this object and the parameter
     * @throws NullPointerException if other is null
     */
    public Number multiply(Number other) throws NullPointerException{
        if(other == null) throw new NullPointerException("Can't be multiplying to Null");
        Node currentThis = this.head;
        Number rec = new Number("0");
        int digit = 0;
        //Multiply by digit
        while (currentThis!=null){
            String out = other.multiplyByDigit(currentThis.data).toString();
            //Add zeros to the end of the result according to the digit.
            for (int i = 0; i < digit; i++) out = out + "0";
            //Add the result to rec
            rec = rec.add(new Number(out));
            currentThis = currentThis.next;
            digit += 1;
        }
        return rec;
    }

    /**
     * Compares this object with other for order.
     * Returns a negative integer if this object's value is strictly smaller than the value of other.
     * Returns a positive integer if this object's value is strictly greater than the value of other.
     * Returns zero if two values are the same.
     * @param other the object to be compared with this object
     * @return a negative integer, zero, or a positive integer
     * as this object is less than, equal to, or greater than other
     * @throws NullPointerException  if other is null
     */
    @Override
    public int compareTo(Number other) throws NullPointerException {
        if(other == null) throw new NullPointerException("Can't be comparing to Null");
        Node currentThis = this.head;
        Node currentOther = other.head;
        int rec = 0;
        int digit;

        //Do comparing until the end of the digits
        while (currentOther!=null && currentThis!=null){
            digit = currentThis.data - currentOther.data;
            if(digit > 0) rec = 1;
            if(digit < 0) rec = -1;
            currentOther = currentOther.next;
            currentThis = currentThis.next;
        }

        //The list longer should be bigger, unless having zeros at the start.
        if(currentOther != null) {
            while (currentOther!=null && currentOther.data == 0) currentOther = currentOther.next;
            if (currentOther == null) return rec;
            return -1;
        }
        if(currentThis != null) {
            while (currentThis!=null && currentThis.data == 0) currentThis = currentThis.next;
            if (currentThis == null) return rec;
            return 1;
        }
        return rec;
    }

    /**
     * Determines if this object is equal to obj.
     * Two Number objects are equal if all of their digits are the same and in the same order,
     * and if they have the same number of digits.
     * In other words, if the values represented by the two objects are the same.
     * @param obj the object to be compared to this object
     * @return true if two objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(!(obj instanceof Number)) return false;
        Number other = (Number) obj;
        if(other.length() != this.length()) return false;
        return this.compareTo(other) == 0;
    }

    /**
     * Returns the string representation of this object.
     * @return string representation of this object
     */
    @Override
    public String toString(){
        String rec = "";
        Node current = this.head;
        while(current!=null){
            rec = current.data + rec;
            current = current.next;
        }
        while (rec.length()>1 && rec.charAt(0) == '0'){
            rec = rec.substring(1);
        }
        return rec;
    }
}
