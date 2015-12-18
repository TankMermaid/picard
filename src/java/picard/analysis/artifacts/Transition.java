package picard.analysis.artifacts;

import htsjdk.samtools.util.SequenceUtil;

enum Transition {
    AtoA('A','A'), AtoC('A','C'), AtoG('A','G'), AtoT('A','T'),
    CtoA('C','A'), CtoC('C','C'), CtoG('C','G'), CtoT('C','T'),
    GtoA('G','A'), GtoC('G','C'), GtoG('G','G'), GtoT('G','T'),
    TtoA('T','A'), TtoC('T','C'), TtoG('T','G'), TtoT('T','T');

    private static final Transition[] ALT_VALUES = new Transition[]{
        AtoC, AtoG, AtoT, CtoA, CtoG, CtoT, GtoA, GtoC, GtoT, TtoA, TtoC, TtoG
    };

    private final char ref;
    private final char call;

    Transition(final char ref, final char call) {
        this.ref = ref;
        this.call = call;
    }

    public static Transition transitionOf(final char ref, final char call) {
        return valueOf(ref + "to" + call);
    }

    /**
     * Like values(), but ignores the ref:ref "transitions".
     */
    public static Transition[] altValues() { return ALT_VALUES; }

    /**
     * Return the complementary transition. Both ref and call must be complemented.
     */
    public Transition complement() {
        return transitionOf((char) SequenceUtil.complement((byte) this.ref), (char) SequenceUtil.complement((byte) this.call));
    }

    public char ref() { return this.ref; }

    public char call() { return this.call; }

    @Override
    public String toString() {
        return this.ref + ">" + this.call;
    }
}