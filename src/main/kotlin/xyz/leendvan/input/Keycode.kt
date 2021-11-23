package xyz.leendvan.input

enum class Keycode(val value:Int) {
    Unknown(-1),
    Mouse_Left(0),
    Mouse_Middle(2),
    Mouse_Right(1),
    Apostrophe(39),
    Comma(44),
    Space(32),
    Equal(61),
    Minus(45),
    Period(46),
    Slash(47),
    Semicolon(59),
    Key_0(48),
    key_1(49),
    Key_2(50),
    Key_3(51),
    Key_4(52),
    Key_5(53),
    Key_6(54),
    Key_7(55),
    Key_8(56),
    Key_9(57),
    A(65),
    B(66),
    C(67),
    D(68),
    E(69),
    F(70),
    G(71),
    H(72),
    I(73),
    J(74),
    K(75),
    L(76),
    M(77),
    N(78),
    O(79),
    P(80),
    Q(81),
    R(82),
    S(83),
    T(84),
    U(85),
    V(86),
    W(87),
    X(88),
    Y(89),
    Z(90),
    Escape(256),
    Enter(257),
    Tab(258),
    Backspace(259),
    Insert(260),
    Delete(261),
    Right(262),
    Left(263),
    Down(264),
    Up(265),
    Page_up(266),
    Page_down(267),
    Home(268),
    End(269),
    Caps_Lock(280),
    Scroll_Lock(281),
    Num_Lock(282),
    Print_Screen(283),
    Pause(284),
    F1(290),
    F2(291),
    F3(292),
    F4(293),
    F5(294),
    F6(295),
    F7(296),
    F8(297),
    F9(298),
    F10(299),
    F11(300),
    F12(301),
    Key_0_S(320),
    Key_1_S(321),
    Key_2_S(322),
    Key_3_S(323),
    Key_4_S(324),
    Key_5_S(325),
    Key_6_S(326),
    Key_7_S(327),
    Key_8_S(328),
    Key_9_S(329),
    Decimal_S(330),
    Divide_S(331),
    Multiply_S(332),
    Substract_S(333),
    Add_S(334),
    Enter_S(335),
    Equal_S(336),
    Left_Shift(340),
    Left_Ctrl(341),
    Left_Alt(342),
    Left_Super(343),
    Right_Shift(344),
    Right_Ctrl(345),
    Right_Alt(346),
    Right_Super(347),
    Menu(348);

    companion object{
        fun getKeyNameFromCode(code:Int):String{
            enumValues<Keycode>().forEach {
                if(it.value == code){
                    return it.name
                }
            }
            return "Unknown"
        }

        fun getKeycodeFromId(code:Int):Keycode{
            enumValues<Keycode>().forEach {
                if(it.value == code){
                    return it
                }
            }
            return Keycode.Unknown
        }
    }
}