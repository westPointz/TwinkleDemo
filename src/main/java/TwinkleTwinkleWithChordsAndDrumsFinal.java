import javax.sound.midi.*;

public class TwinkleTwinkleWithChordsAndDrumsFinal {
  public static void main(String[] args) throws MidiUnavailableException, InterruptedException {
    // 创建一个MIDI设备
    Synthesizer synth = MidiSystem.getSynthesizer();
    synth.open();

    // 获取MIDI通道
    MidiChannel melodyChannel = synth.getChannels()[0];
    MidiChannel chordChannel = synth.getChannels()[1];
    MidiChannel drumsChannel = synth.getChannels()[9];

    // 设置乐器
    melodyChannel.programChange(0);
    chordChannel.programChange(0);

    // 定义小星星的主旋律
    int[] melodyNotes = {60, 60, 67, 67, 69, 69, 67, 65, 65, 64, 64, 62, 62, 60};

    // 定义小星星的主旋律节奏
    int[] melodyRhythm = {500, 500, 500, 500, 500, 500, 1000, 500, 500, 500, 500, 500, 500, 1000};

    // 定义小星星的和弦
    int[] chordNotes = {60, 64, 67};

    // 定义小星星的和弦节奏
    int[] chordRhythm = {2000};

    // 演奏小星星的主旋律和和弦


    // 创建一个新的线程来演奏架子鼓
    new Thread(() -> {
      try {
        drumsChannel.programChange(0, 118);
        drumsChannel.noteOn(42, 100);
        Thread.sleep(500);
        drumsChannel.noteOn(38, 100);
        Thread.sleep(500);
        drumsChannel.noteOn(42, 100);
        Thread.sleep(500);
        drumsChannel.noteOn(38, 100);
        Thread.sleep(500);
        drumsChannel.noteOn(46, 100);
        Thread.sleep(1000);
        drumsChannel.noteOn(42, 100);
        Thread.sleep(500);
        drumsChannel.noteOn(38, 100);
        Thread.sleep(500);
        drumsChannel.noteOn(42, 100);
        Thread.sleep(500);
        drumsChannel.noteOn(38, 100);
        Thread.sleep(500);
        drumsChannel.noteOn(49, 100);
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();

    for (int i = 0; i < melodyNotes.length; i++) {
      melodyChannel.noteOn(melodyNotes[i], 100);
      for (int j = 0; j < chordNotes.length; j++) {
        chordChannel.noteOn(chordNotes[j], 50);
      }
      Thread.sleep(melodyRhythm[i]);
      melodyChannel.noteOff(melodyNotes[i]);
      for (int j = 0; j < chordNotes.length; j++) {
        chordChannel.noteOff(chordNotes[j]);
      }
    }
    // 停止演奏
    melodyChannel.allNotesOff();
    chordChannel.allNotesOff();
    drumsChannel.allNotesOff();
  }
}
