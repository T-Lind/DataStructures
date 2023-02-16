package turingmachine

fun intToBool(input: Int): Boolean{
    return input == 1
}

class Observer(var tape: Tape) {
    private var position = 0

    fun moveTo(x: Int): Boolean {
        if(0 >= x && x < tape.tapeLen!!){
            position = x
            return true
        }
        return false
    }
    fun write(state: Int){
        tape.tapeData?.set(position, intToBool(state))
    }
}