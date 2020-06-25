package com.mygradle.commons.common;

import java.util.*;
public class MemberCheck {
    public static void main(String[] args) {//server 안에 userlist 저장, userlist는 <id,User클래스> 쌍으로 이루어져 있다.
        boolean is_loginned=false;//누군가 로그인되어 있으면 true, 다른 사람의 로그인을 막는다
        int input;//메뉴 번호를 받는 변수
        User user_temp=null;
        Server server = new Server();
        System.out.println("회원 카드 시스템 프로토타입입니다. 이하의 메뉴에서 원하시는 것을 선택 후 enter키를 눌러주십시오.");
        Scanner sc=new Scanner(System.in);
        while(true) {
            System.out.println("1: 로그인\n2. 로그아웃\n3. 물품 구매\n4. 입금\n5. 회원 카드 등급 변경\n6. 회원 가입\n7. 회원 정보 변경"
                    + "\n8. 회원 탙퇴\n9. 회원 구매 기록 조회\n10. 회원 로그인/로그아웃 기록 조회");
            input=sc.nextInt();
            switch(input) {
                case 1:
                    if(is_loginned==true) {
                        System.out.println("로그아웃 후 이용해주십시오.");
                        break;
                    }
                    System.out.print("id 입력 : ");
                    String id=sc.next();
                    System.out.print("password 입력 : ");
                    String password=sc.next();
                    if(server.user_list.get(id)==null||!server.user_list.get(id).getPassword().equals(password)) {
                        System.out.println("회원 정보를 찾을 수 없습니다. 다시 시도해주십시오.");
                        break;
                    }
                    is_loginned=true;
                    user_temp=server.user_list.get(id);
                    user_temp.addAttendance(new Pair(getDate(),true));//String을 이렇게 반환값으로 둬도 되나? new를 이렇게 써도 되나?
                    System.out.println(user_temp.getName()+"님 로그인되었습니다.");
                    break;
                case 2:
                    if(is_loginned==false) {
                        System.out.println("로그인 후 이용해주십시오.");
                        break;
                    }
                    user_temp.addAttendance(new Pair(getDate(),false));
                    user_temp=null;
                    is_loginned=false;
                    System.out.println("정상적으로 로그아웃되었습니다.");
                    break;
                case 3:
                    if(is_loginned==false) {
                        System.out.println("로그인 후 이용해주십시오.");
                        break;
                    }
                    System.out.print("구매할 물품명을 입력해주십시오: ");
                    String item=sc.next();
                    System.out.print("총 금액을 입력해주십시오: ");
                    int amount=sc.nextInt();
                    if(user_temp.withdraw(item, amount)==-1) {
                        System.out.println("다시 시도해주십시오");
                        break;
                    }
                    System.out.println("정상적으로 처리되었습니다. 감사합니다.");
                    break;
                case 4:
                    if(is_loginned==false) {
                        System.out.println("로그인 후 이용해주십시오.");
                        break;
                    }
                    System.out.print("입금하실 금액을 입력해주십시오 : ");
                    int deposit=sc.nextInt();
                    if(deposit<=0) {
                        System.out.println("금액은 0 이하일 수 없습니다. 다시 시도해주십시오."); //금액이 0 이하인 경우의 오류를 문서에 추가하기
                        break;
                    }
                    user_temp.deposit(deposit);
                    System.out.println(deposit+"입금 후 잔액 : "+user_temp.balance);
                    break;
                case 5:
                    if(is_loginned==false) {
                        System.out.println("로그인 후 이용해주십시오.");
                        break;
                    }
                    System.out.print("카드 등급을 선택해주십시오:\n1. 연회비 30000원, 할인율 1%\n2. 연회비 50000원, 할인율 3%\n3. 연회비 100000원, 할인율 9%\n");
                    int modified_grade=sc.nextInt();
                    if(user_temp.card_grade==modified_grade||modified_grade<1||modified_grade>3) {
                        System.out.println("해당되는 등급이 존재하지 않거나 현재 등급과 같은 경우 변경이 불가합니다. 다시 시도해주십시오.");
                        break;
                    }
                    if(user_temp.modifyCardGrade(modified_grade)==-1) {
                        System.out.println("다시 시도해주십시오.");
                        break;
                    }
                    System.out.println("정상적으로 처리되었습니다. 감사합니다");
                    break;
                case 6://문제가 되는 부분, 정보를 모두 입력하고 출금 처리가 되면 outofmemory 에러 발생
                    if(is_loginned==true) {
                        System.out.println("회원가입은 로그아웃 후 이용해주십시오.");
                        break;
                    }
                    System.out.print("id를 입력해주십시오: ");
                    String new_id=sc.next();
                    if(server.user_list.get(new_id)!=null) {
                        System.out.println("이미 존재하는 id입니다. 다시 시도해주십시오.");
                        break;
                    }
                    System.out.print("password를 입력해주십시오: ");
                    String new_password=sc.next();
                    System.out.print("이름을 입력해주십시오: ");
                    String new_name=sc.next();
                    System.out.print("카드 등급을 선택해주십시오:\n1. 연회비 30000원, 할인율 1%\n2. 연회비 50000원, 할인율 3%\n3. 연회비 100000원, 할인율 9%\n");
                    int new_grade=sc.nextInt();
                    user_temp=new User(new_id,new_password,new_name,new_grade);
                    while(user_temp.balance<user_temp.annual_fee) {
                        int add_amount;
                        System.out.print("입금하실 금액을 입력해주십시오(최초 회원 가입이므로 선택한 등급의 카드의 연회비보다 많은 금액의 입금이 필요합니다): ");
                        add_amount=sc.nextInt();
                        user_temp.deposit(add_amount);
                    }
                    user_temp.withdraw("연회비",user_temp.annual_fee);
                    server.user_list.put(new_id, user_temp);
                    System.out.println(user_temp.getName()+"님 정상적으로 회원가입되었습니다. 등록된 회원 정보로 로그인해주시기 바랍니다.");
                    user_temp=null;
                    break;
                case 7:
                    if(is_loginned==false) {
                        System.out.println("로그인 후 이용해주십시오.");
                        break;
                    }
                    System.out.println("어느 정보를 수정하시겠습니까?\n1. id 2. password 3. 이름");
                    int choice=sc.nextInt();
                    switch(choice) {
                        case 1:
                            System.out.print("새로운 id를 입력해주십시오: ");
                            String modified_id=sc.next();
                            server.user_list.delete(user_temp.getID());
                            user_temp.modifyID(modified_id);
                            server.user_list.put(modified_id, user_temp);
                            System.out.println("변경 완료되었습니다. 감사합니다.");
                            break;
                        case 2:
                            System.out.print("새로운 password를 입력해주십시오: ");
                            String modified_password=sc.next();
                            //server.user_list.delete(user_temp.getID());
                            user_temp.modifyPassword(modified_password);
                            //server.user_list.put(user_temp.getID(), user_temp);
                            System.out.println("변경 완료되었습니다. 감사합니다.");
                            break;
                        case 3:
                            System.out.print("새로운 name을 입력해주십시오: ");
                            String modified_name=sc.next();
                            //server.user_list.delete(user_temp.getID());
                            user_temp.modifyName(modified_name);
                            //server.user_list.put(user_temp.getID(), user_temp); 이 때 java가 call by reference라면 여기의 주석 처리된 줄들을 지운다
                            System.out.println("변경 완료되었습니다. 감사합니다.");
                            break;
                        default:
                            System.out.println("존재하지 않는 메뉴입니다. 다시 시도해주십시오.");
                            break;
                    }
                    break;
                case 8:
                    System.out.println("정말 탈퇴하시겠습니까? 탈퇴를 위해 회원 본인의 id와 password를 다시 입력해주십시오.");
                    System.out.print("id: ");
                    String check_id=sc.next();
                    System.out.print("password: ");
                    String check_password=sc.next();
                    if(check_id.equals(user_temp.getID())&&check_id.equals(user_temp.getPassword())) {
                        server.user_list.delete(check_id);
                        user_temp=null;
                        is_loginned=false;
                        System.out.println("회원 탈퇴 처리가 완료되었습니다. 지금까지 이용해주셔서 감사합니다.");
                        break;
                    }
                    System.out.println("id 또는 password가 일치하지 않습니다. 다시 시도해주시기 바랍니다.");
                    break;
                case 9:
                    if(is_loginned==false) {
                        System.out.println("로그인 후 이용해주십시오.");
                        break;
                    }
                    System.out.println(user_temp.getName()+"님의 구매 기록:");
                    user_temp.withdraw_record.print();
                    break;
                case 10:
                    if(is_loginned==false) {
                        System.out.println("로그인 후 이용해주십시오.");
                        break;
                    }
                    System.out.println(user_temp.getName()+"님의 출입 기록:");
                    user_temp.printLog();
                    break;
                default:
                    System.out.println("존재하지 않는 메뉴입니다. 다시 시도해주십시오.");
                    break;
            }
        }
    }
    public static String getDate() {
        Date d = new Date();
        String s = d.toString();
        return s;
    }
}
class Account{
    protected int balance;
    protected LinearProbingHashST<String, Integer> withdraw_record;
    protected void deposit(int amount) {
        this.balance+=amount;
    }
    protected int withdraw(String cause, int amount) {
        if(this.balance<amount) {
            System.out.println("잔액 부족, 출금 불가");
            return -1;
        }
        this.balance-=amount;
        withdraw_record.put(cause, amount); //이 부분을 주석 처리할지에 대해서는 용량 문제가 해결됨에 따라 결정
        System.out.println(amount+" 출금 후 "+this.balance+" 남았습니다.");
        return this.balance;
    }
}
class Card extends Account{
    protected int card_grade;
    protected int annual_fee;
    protected double discount;
    protected void setCardInfo(int card_grade) {
        switch(card_grade) {
            case 1:
                this.annual_fee=30000;
                this.discount=0.99;
                break;
            case 2:
                this.annual_fee=50000;
                this.discount=0.97;
                break;
            case 3:
                this.annual_fee=100000;
                this.discount=0.91;
                break;
        }
    }
}
class User extends Card{
    private String id;
    private String password;
    private String name;
    public ArrayList<Pair> attendance_record=new ArrayList<Pair>();
    User(String id, String password, String name, int card_grade){
        this.id=id;
        this.password=password;
        this.name=name;
        setCardInfo(card_grade);
        withdraw_record = new LinearProbingHashST<>(30);
    }
    public void addAttendance(Pair attendance) {
        this.attendance_record.add(attendance);
    }
    public String getID() {
        return id;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    /*public int purchase(int amount) {
      if(this.balance<amount) {
        System.out.println("잔액 부족, 출금 불가");
        return -1;
     }
     this.balance-=amount*discount;
     return this.balance;
    }*/ //purchase는 withdraw가 완전히 대체 가능한가?
    public void modifyID(String new_id) {
        this.id=new_id;
    }
    public void modifyPassword(String new_password) {
        this.password=new_password;
    }
    public void modifyName(String new_name) {
        this.name=new_name;
    }
    public int modifyCardGrade(int grade) {
        if(grade==1) { //등급이 1인 경우
            this.annual_fee=30000;
            this.card_grade=1;
            return 1;
        }
        else if(grade==2) { //등급이 2인 경우
            if(this.card_grade<grade)
                if(withdraw("추가 연회비",50000-this.annual_fee)==-1)
                    return -1;
            this.annual_fee=50000;
            this.card_grade=2;
            return 1;
        }
        else { //등급이 3인 경우
            if(this.card_grade<grade)
                if(withdraw("추가 연회비",100000-this.annual_fee)==-1)
                    return -1;
            this.annual_fee=100000;
            this.card_grade=3;
            return 1;
        }
    }
    public void printLog() {
        for(int count=0;count<attendance_record.size();count++) {
            if(attendance_record.get(count).getEnterance()==true)
                System.out.println(attendance_record.get(count).getTime()+" 로그인");
            else
                System.out.println(attendance_record.get(count).getTime()+" 로그아웃");
        }
    }
}
class Pair{
    private String time;
    private boolean is_enterance;
    Pair(String time, boolean is_enterance){
        this.time=time;
        this.is_enterance=is_enterance;
    }
    public String getTime() {
        return this.time;
    }
    public boolean getEnterance() {
        return this.is_enterance;
    }
}
class Server{
    public CuckooHashingST<String, User> user_list= new CuckooHashingST<>(50, 0.75);
}
class LinearProbingHashST<K,V>{ //출금 기록
    private int data;
    private int address_size;
    private K[] keys;
    private V[] values;
    public int getBiggerAddressSize(int address_size) {
        int find_proper_divisor;
        for(int count=address_size;;count++) {
            for(find_proper_divisor=2;find_proper_divisor<count/2;find_proper_divisor++)
                if(count%find_proper_divisor==0) break;
            if((find_proper_divisor>=count/2)&&(address_size%count!=0))
                return count;
        }
    }
    public LinearProbingHashST(int size) {
        this.address_size=getBiggerAddressSize(size);
        keys=(K[])new Object[address_size];
        values=(V[])new Object[address_size];
    }
    public V get(K key) {
        for(int search_index=hash(key);keys[search_index]!=null;search_index=(search_index+1)%address_size)
            if(keys[search_index].equals(key))
                return values[search_index];
        return null;
    }
    public void put(K key, V value) {
        if(data>=address_size/2)
            resize(2*address_size+1);
        int search_index;
        for(search_index=hash(key);keys[search_index]!=null;search_index=(search_index+1)%address_size)
            if(keys[search_index].equals(key))
                values[search_index]=value;
        keys[search_index]=key;
        values[search_index]=value;
        data++;
    }
    public void print() {
        for(int count=0;count<this.address_size;count++)
            if(keys[count]!=null)
                System.out.println(keys[count]+", "+values[count]+"원");
    }
    private void resize(int cap) {
        LinearProbingHashST<K,V> t;
        t=new LinearProbingHashST<>(cap);
        for(int i=0;i<address_size;i++)
            if(keys[i]!=null)
                t.put(keys[i],values[i]);
        keys=t.keys;
        values=t.values;
        address_size=t.address_size;
    }
    public boolean contains(K key) {return get(key)!=null;}
    public boolean isEmpty() {return data==0;}
    public int size() {return data;}
    private int hash(K key) {return (key.hashCode()&0x7fffffff)%address_size;}
}
class CuckooHashingST<K,V>{ //user_list
    private int data;
    private int total_address_size;
    private int address_size;
    private int smaller_address_size;
    private double fill_factor;
    private double max_loop;
    private K[] keys;
    private V[] values;
    private K[] keys2;
    private V[] values2;
    public CuckooHashingST(int size, double fill_factor) {
        this.total_address_size=getAddressSize(size);
        this.address_size=getAddressSize(total_address_size/2+1);
        this.smaller_address_size=total_address_size-address_size;
        this.fill_factor=fill_factor;
        this.max_loop=3*Math.log(total_address_size*fill_factor)/Math.log(1+0.5-fill_factor);
        keys=(K[])new Object[address_size];
        values=(V[])new Object[address_size];
        keys2=(K[])new Object[smaller_address_size];
        values2=(V[])new Object[smaller_address_size];
    }
    public int getAddressSize(int address_size) {
        int find_proper_divisor;
        for(int count=address_size;;count++) {
            for(find_proper_divisor=2;find_proper_divisor<=count/2;find_proper_divisor++)
                if(count%find_proper_divisor==0) break;
            if((find_proper_divisor>count/2))
                return count;
        }
    }
    public V get(K key) { //회원 존재 여부 판단, 회원 정보 클래스 불러오기
        if(keys[hash(key)]!=null&&keys[hash(key)].equals(key))
            return values[hash(key)];
        else if(keys2[hash2(key)]!=null&&keys2[hash2(key)].equals(key))
            return values2[hash2(key)];
        else
            return null;
    }
    public void put(K key, V value) { //회원 가입
        K key_temp;
        V value_temp;
        if(get(key)!=null) {
            if(keys[hash(key)].equals(key))
                values[hash(key)]=value;
            else if(keys2[hash2(key)].equals(key))
                values2[hash2(key)]=value;
        }
        int count_limit=0;
        while(count_limit<max_loop) {
            if(keys[hash(key)]==null) {
                keys[hash(key)]=key;
                values[hash(key)]=value;
                data++;
            }
            key_temp=keys[hash(key)];
            value_temp=values[hash(key)];
            keys[hash(key)]=key;
            values[hash(key)]=value;
            key=key_temp;
            value=value_temp;
            if(keys2[hash2(key)]==null) {
                keys2[hash2(key)]=key;
                values2[hash2(key)]=value;
                data++;
            }
            key_temp=keys2[hash2(key)];
            value_temp=values2[hash2(key)];
            keys2[hash2(key)]=key;
            values2[hash2(key)]=value;
            key=key_temp;
            value=value_temp;
            count_limit++;
        }
        resize(total_address_size*2+1);
        put(key,value);
    }
    public void delete(K key) { //회원 탈퇴
        if(get(key)!=null) {
            if(keys[hash(key)].equals(key)) {
                keys[hash(key)]=null;
                values[hash(key)]=null;
            }
            else {
                keys2[hash2(key)]=null;
                values2[hash2(key)]=null;
            }
        }
        else
            System.out.println("데이터를 찾을 수 없습니다.");
    }
    private void resize(int cap) {
        CuckooHashingST<K,V> t;
        t=new CuckooHashingST<>(cap, fill_factor);
        for(int i=0;i<address_size;i++)
            if(keys[i]!=null)
                t.put(keys[i],values[i]);
        for(int i=0;i<smaller_address_size;i++)
            if(keys2[i]!=null)
                t.put(keys2[i], values2[i]);
        keys=t.keys;
        values=t.values;
        keys2=t.keys2;
        values2=t.values2;
        total_address_size=t.total_address_size;
        address_size=t.address_size;
        smaller_address_size=t.smaller_address_size;
        max_loop=t.max_loop;
    }
    public boolean contains(K key) {return get(key)!=null;}
    public boolean isEmpty() {return data==0;}
    public int size() {return data;}
    private int hash(K key) {return (key.hashCode()&0x7fffffff)%address_size;}
    private int hash2(K key) {return (key.hashCode()&0x7fffffff)%smaller_address_size;}
}
