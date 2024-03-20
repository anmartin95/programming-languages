open System
open System.Threading

// convert 8bit binary list to unsigned integer
let base2listtoUnsigned base2list =
    let rec loop base2list acc pow2 =
        if base2list = [] then acc
        else loop base2list.Tail (acc + (base2list.Head * pow2)) (pow2 / 2)
    loop base2list 0 128

// convert string to int of given base
let StringToInt string (toBase:int) = Convert.ToInt32(string, toBase)

// convert unsigned number to an 8bit binary list
let unsignedtobase2list unsigned = 
    let rec loop num list pow = 
        if pow = 0 then list
        else loop (num % pow) (list @ [(num/pow)]) (pow >>> 1)
    loop unsigned [] 128

// take the NOT of a 8bit binary list (0->1, 1->0)
let NOT base2list = 
    let rec opposite list notlist =
        match list with
        | [] -> notlist
        | head::tail -> opposite list.Tail (notlist @ [(if list.Head = 0 then 1 else 0)])
    opposite base2list []

// add 1 to an 8bit binary list (for twos compliment)
let addone list = 
    let rec addonetobase2 list carry =
        match list with
        | [] -> if carry = 1 then [1] else []
        | tail::head->
            let sum = tail + carry
            (sum % 2)::(addonetobase2 head (if sum > 1 then 1 else 0))
    addonetobase2 (List.rev list) 1 |> List.rev

// subtract one  from 8bit binary list (to reverse 2s compliment for converting list to negative number)
let subone list =
    let rec subtractOne lst borrow =
        match lst with
        | [] -> if borrow = 1 then failwith "Cannot subtract 1 from 0" else [] // test if binary list is all zeros
        | 0::rest -> if borrow = 1 then 1::(subtractOne rest 1) else 0::rest
        | 1::rest -> if borrow = 1 then 0::(subtractOne rest 0) else 1::rest
        | _ -> failwith "Invalid binary digit" // test if list is invalid
    subtractOne (List.rev list) 1 |> List.rev

// handle 2's compliment if needed (only for artihmetic operations)
let signedtobase2list signed = 
    if signed <> (abs signed) then 
        addone(NOT(unsignedtobase2list (abs signed)))
    else 
        unsignedtobase2list signed

// handle reversing 2's compliment if needed (only arithmetic operations)
let base2listtosigned base2list sign =
    if sign then
        -1*(base2listtoUnsigned(NOT (subone base2list)))
    else
        base2listtoUnsigned base2list

// take the OR of two lists - 1,0 or 0,1 or 1,1 but not 0,0
let OR base2list1 base2list2 = 
    let rec orlists list1 list2 acc =
        match list1, list2 with
        | [], [] | _, [] | [], _ -> acc
        | head1::tail1, head2::tail2 -> orlists list1.Tail list2.Tail (acc @ [(if (list1.Head = 1 || list2.Head = 1) then 1 else 0)])
    orlists base2list1 base2list2 []

// take the AND of two lists - only 1,1
let AND base2list1 base2list2 =
    let rec andlists list1 list2 acc =
        match list1, list2 with
        | [], [] | _, [] | [], _ -> acc
        | head1:: tail1, head2::tail2 -> andlists list1.Tail list2.Tail (acc @ [(if (list1.Head = 1 && list2.Head = 1) then 1 else 0)])
    andlists base2list1 base2list2 []

// take the XOR of two lists - 0,1 or 1,0 only
let XOR base2list1 base2list2 =
    let rec xorlists list1 list2 acc =
        match list1, list2 with
        | [], [] | _, [] | [], _ -> acc
        | head1::tail1, head2::tail2 -> xorlists list1.Tail list2.Tail (acc @ [(if ((list1.Head = 1 || list2.Head = 1) && list1.Head <> list2.Head) then 1 else 0)])
    xorlists base2list1 base2list2 []

// add two 8bit binary lists
let ADD base2list1 base2list2 = 
    let rec addlists list1 list2 carry = 
        match list1, list2 with
        | [], [] | _, [] | [], _ -> []
        | tail::head, tail2::head2 ->
            let sum = tail + tail2 + carry
            (sum % 2)::(addlists head head2 (if sum> 1 then 1 else 0))
    addlists (List.rev base2list1) (List.rev base2list2) 0 |> List.rev

// subtract two 8bit binary lists - taking the twos compliment of second list and adding it to first
let SUB base2list1 base2list2 = 
    let rec addlists list1 list2 carry = 
        match list1, list2 with
        | [], [] | _, [] | [], _ -> []
        | tail::head, tail2::head2 ->
            let sum = tail + tail2 + carry
            (sum % 2)::(addlists head head2 (if sum> 1 then 1 else 0))
    addlists (List.rev base2list1) (List.rev (addone (NOT base2list2))) 0 |> List.rev // take twos compliment and apply add

// console interface
let result () =
    printf "\nEnter the operation you want to perform (NOT, OR, AND, XOR, ADD, SUB or QUIT): "
    match Console.ReadLine() with
    | "NOT" ->
        printf "Enter hex value: "
        let byte = Console.ReadLine()
        let tmp = (NOT (StringToInt byte 16 |> unsignedtobase2list))
        printfn $"Result of NOT %s{byte} = %A{tmp} = %X{base2listtoUnsigned tmp}"
        true
    | "OR" ->
        printf "Enter first hex value: "
        let h1 = Console.ReadLine()
        printf "Enter second hex value: "
        let h2 = Console.ReadLine()
        let binlist1 = StringToInt h1 16 |> unsignedtobase2list
        let binlist2 = StringToInt h2 16 |> unsignedtobase2list
        let tmp = (OR binlist1 binlist2)
        printfn $"\t%A{binlist1} = %s{h1}"
        printfn $"OR\t%A{binlist2} = %s{h2}"
        printfn $"--------------------------------"
        printfn $"\t%A{tmp} = %X{base2listtoUnsigned tmp}"
        true
    | "AND" ->
        printf "Enter first hex value: "
        let h1 = Console.ReadLine()
        printf "Enter second hex value: "
        let h2 = Console.ReadLine()
        let binlist1 = StringToInt h1 16 |> unsignedtobase2list
        let binlist2 = StringToInt h2 16 |> unsignedtobase2list
        let tmp = (AND binlist1 binlist2)
        printfn $"\t%A{binlist1} = %s{h1}"
        printfn $"AND\t%A{binlist2} = %s{h2}"
        printfn $"--------------------------------"
        printfn $"\t%A{tmp} = %X{base2listtoUnsigned tmp}"
        true
    | "XOR" ->
        printf "Enter first hex value: "
        let h1 = Console.ReadLine()
        printf "Enter second hex value: "
        let h2 = Console.ReadLine()
        let binlist1 = StringToInt h1 16 |> unsignedtobase2list
        let binlist2 = StringToInt h2 16 |> unsignedtobase2list
        let tmp = (XOR binlist1 binlist2)
        printfn $"\t%A{binlist1} = %s{h1}"
        printfn $"XOR\t%A{binlist2} = %s{h2}"
        printfn $"--------------------------------"
        printfn $"\t%A{tmp} = %X{base2listtoUnsigned tmp}"
        true
    | "ADD" ->
        printf "Enter first decimal value: "
        let n1 = Console.ReadLine()
        printf "Enter second decimal value: "
        let n2 = Console.ReadLine()
        let num1 = StringToInt n1 10
        let num2 = StringToInt n2 10
        let binlist1 = signedtobase2list num1
        let binlist2 = signedtobase2list num2
        let tmp = (ADD binlist1 binlist2)
        let sign = if (num1+num2>=0 && num1+num2<=127) then false else true
        printfn $"\t%A{binlist1} = %s{n1}"
        printfn $"ADD\t%A{binlist2} = %s{n2}"
        printfn $"--------------------------------"
        printfn $"\t%A{tmp} = {base2listtosigned tmp sign}"
        true
    | "SUB" ->
        printf "Enter first decimal value: "
        let n1 = Console.ReadLine()
        printf "Enter second decimal value: "
        let n2 = Console.ReadLine()
        let num1 = StringToInt n1 10
        let num2 = StringToInt n2 10 
        let binlist1 = signedtobase2list num1
        let binlist2 = signedtobase2list num2
        let tmp = (SUB binlist1 binlist2)
        let sign = if (num1+num2>=0 && num1+num2<=127) then false else true
        printfn $"\t%A{binlist1} = %s{n1}"
        printfn $"SUB\t%A{addone (NOT binlist2)} = %s{n2}"
        printfn $"--------------------------------"
        printfn $"\t%A{tmp} = {base2listtosigned tmp sign}"
        true
    | "QUIT" ->
        false

// run program with console interface
let run =
    while result() <> false do result