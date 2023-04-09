import javax.sound.midi.*;

public class TwinkleTwinkle {
    public static void main(String[] args) throws MidiUnavailableException, InterruptedException {
        // 创建一个MIDI设备
        Synthesizer synth = MidiSystem.getSynthesizer();
        synth.open();

        // 获取MIDI通道
        MidiChannel channel = synth.getChannels()[0];

        // 设置乐器
        channel.programChange(0);

        // 定义小星星的音符
        int[] notes = {60, 60, 67, 67, 69, 69, 67, 65, 65, 64, 64, 62, 62, 60};

        // 定义小星星的节奏
        int[] rhythm = {500, 500, 500, 500, 500, 500, 1000, 500, 500, 500, 500, 500, 500, 1000};

        // 演奏小星星的主旋律
        for (int i = 0; i < notes.length; i++) {
            channel.noteOn(notes[i], 100);
            Thread.sleep(rhythm[i]);
            channel.noteOff(notes[i]);
        }

        // 关闭MIDI设备
        synth.close();
    }
}
