import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        //String[] args = new String[]{"Test_operation_binaire.s"};

        HashMap<AbstractMap.SimpleEntry<String, String>, AbstractMap.SimpleEntry<String, Integer>> instruction_all = new HashMap<>();
        // Shift, add, sub, mov
        instruction_all.put(new AbstractMap.SimpleEntry<>("LSLS", "IMMEDIAT"), new AbstractMap.SimpleEntry<>("00000", 5));
        instruction_all.put(new AbstractMap.SimpleEntry<>("LSRS", "IMMEDIAT"), new AbstractMap.SimpleEntry<>("00001", 5));
        instruction_all.put(new AbstractMap.SimpleEntry<>("ASRS", "IMMEDIAT"), new AbstractMap.SimpleEntry<>("00010", 5));
        instruction_all.put(new AbstractMap.SimpleEntry<>("ADDS", "REGISTRE"), new AbstractMap.SimpleEntry<>("0001100", 0));
        instruction_all.put(new AbstractMap.SimpleEntry<>("SUBS", "REGISTRE"), new AbstractMap.SimpleEntry<>("0001101", 0));
        instruction_all.put(new AbstractMap.SimpleEntry<>("ADDS", "IMMEDIAT"), new AbstractMap.SimpleEntry<>("0001110", 3));
        instruction_all.put(new AbstractMap.SimpleEntry<>("SUBS", "IMMEDIAT"), new AbstractMap.SimpleEntry<>("0001111", 3));
        instruction_all.put(new AbstractMap.SimpleEntry<>("MOVS", "IMMEDIAT"), new AbstractMap.SimpleEntry<>("00100", 8));

        instruction_all.put(new AbstractMap.SimpleEntry<>("CMP", "IMMEDIAT"), new AbstractMap.SimpleEntry<>("00101", 8));

        // Data processing
        instruction_all.put(new AbstractMap.SimpleEntry<>("ANDS", "REGISTRE"), new AbstractMap.SimpleEntry<>("0100000000", 0));
        instruction_all.put(new AbstractMap.SimpleEntry<>("EORS", "REGISTRE"), new AbstractMap.SimpleEntry<>("0100000001", 0));
        instruction_all.put(new AbstractMap.SimpleEntry<>("LSLS", "REGISTRE"), new AbstractMap.SimpleEntry<>("0100000010", 0));
        instruction_all.put(new AbstractMap.SimpleEntry<>("LSRS", "REGISTRE"), new AbstractMap.SimpleEntry<>("0100000011", 0));
        instruction_all.put(new AbstractMap.SimpleEntry<>("ASRS", "REGISTRE"), new AbstractMap.SimpleEntry<>("0100000100", 0));
        instruction_all.put(new AbstractMap.SimpleEntry<>("ADCS", "REGISTRE"), new AbstractMap.SimpleEntry<>("0100000101", 0));
        instruction_all.put(new AbstractMap.SimpleEntry<>("SBCS", "REGISTRE"), new AbstractMap.SimpleEntry<>("0100000110", 0));
        instruction_all.put(new AbstractMap.SimpleEntry<>("RORS", "REGISTRE"), new AbstractMap.SimpleEntry<>("0100000111", 0));
        instruction_all.put(new AbstractMap.SimpleEntry<>("TST", "REGISTRE"), new AbstractMap.SimpleEntry<>("0100001000", 0));
        instruction_all.put(new AbstractMap.SimpleEntry<>("RSBS", "IMMEDIAT"), new AbstractMap.SimpleEntry<>("0100001001", 0));
        instruction_all.put(new AbstractMap.SimpleEntry<>("CMP", "REGISTRE"), new AbstractMap.SimpleEntry<>("0100001010", 0));
        instruction_all.put(new AbstractMap.SimpleEntry<>("CMN", "REGISTRE"), new AbstractMap.SimpleEntry<>("0100001011", 0));
        instruction_all.put(new AbstractMap.SimpleEntry<>("ORRS", "REGISTRE"), new AbstractMap.SimpleEntry<>("0100001100", 0));
        instruction_all.put(new AbstractMap.SimpleEntry<>("MULS", "REGISTRE"), new AbstractMap.SimpleEntry<>("0100001101", 0));
        instruction_all.put(new AbstractMap.SimpleEntry<>("BICS", "REGISTRE"), new AbstractMap.SimpleEntry<>("0100001110", 0));
        instruction_all.put(new AbstractMap.SimpleEntry<>("MVNS", "REGISTRE"), new AbstractMap.SimpleEntry<>("0100001111", 0));

        // Load/Store
        instruction_all.put(new AbstractMap.SimpleEntry<>("STR", "IMMEDIAT"), new AbstractMap.SimpleEntry<>("10010", 8));
        instruction_all.put(new AbstractMap.SimpleEntry<>("LDR", "IMMEDIAT"), new AbstractMap.SimpleEntry<>("10011", 8));
        instruction_all.put(new AbstractMap.SimpleEntry<>("ADD", "IMMEDIAT"), new AbstractMap.SimpleEntry<>("101100000", 7));
        instruction_all.put(new AbstractMap.SimpleEntry<>("SUB", "IMMEDIAT"), new AbstractMap.SimpleEntry<>("101100001", 7));

        instruction_all.put(new AbstractMap.SimpleEntry<>("B", "IMMEDIAT"), new AbstractMap.SimpleEntry<>("1101", 8));

        Map<String, String> conditions = new HashMap<>();
        conditions.put("EQ", "0000");
        conditions.put("NE", "0001");
        conditions.put("CS", "0010");
        conditions.put("CC", "0011");
        conditions.put("MI", "0100");
        conditions.put("PL", "0101");
        conditions.put("VS", "0110");
        conditions.put("VC", "0111");
        conditions.put("HI", "1000");
        conditions.put("LS", "1001");
        conditions.put("GE", "1010");
        conditions.put("LT", "1011");
        conditions.put("GT", "1100");
        conditions.put("LE", "1101");
        conditions.put("AL", "1110");

        Map<String, Integer> label = new HashMap<>();
        List<String> instructions = new ArrayList<>();

        int pc = 0;

        try {
            File myObj = new File(args[0]);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                List<String> ligne = new ArrayList<>();
                Collections.addAll(ligne, data.split("\\s+"));
                if (ligne.get(0).equals("")) {
                    ligne.remove(0);
                }
                if (!ligne.isEmpty()) {
                    if (ligne.get(0).endsWith(":")) {
                        label.put(ligne.get(0).substring(0, ligne.get(0).length() - 1), pc);
                        if (ligne.size() > 1 && (instruction_all.containsKey(new AbstractMap.SimpleEntry<>(ligne.get(1).toUpperCase(), "IMMEDIAT")) ||
                                instruction_all.containsKey(new AbstractMap.SimpleEntry<>(ligne.get(1).toUpperCase(), "REGISTRE")))) {
                            instructions.add(ligne.get(1));
                            pc++;
                        } else if (ligne.size() > 1 && instruction_all.containsKey(new AbstractMap.SimpleEntry<>(ligne.get(1).substring(0, 1).toUpperCase(), "IMMEDIAT")) && conditions.containsKey(ligne.get(1).substring(1))) {
                            instructions.add(data);
                            pc++;
                        }
                    }
                    if (instruction_all.containsKey(new AbstractMap.SimpleEntry<>(ligne.get(0).toUpperCase(), "IMMEDIAT")) ||
                            instruction_all.containsKey(new AbstractMap.SimpleEntry<>(ligne.get(0).toUpperCase(), "REGISTRE"))) {
                        instructions.add(data);
                        pc++;
                    } else if (instruction_all.containsKey(new AbstractMap.SimpleEntry<>(ligne.get(0).substring(0, 1).toUpperCase(), "IMMEDIAT")) && conditions.containsKey(ligne.get(0).substring(1).toUpperCase())) {
                        instructions.add(data);
                        pc++;
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred with the file");
            e.printStackTrace();
        }

        pc = 0;

        String fileName = args[0];
        fileName = fileName.replaceAll("(\\..?$)", ".bin");
        File file = new File(fileName);

        if (!file.exists())
            file.createNewFile();

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("v2.0 raw\n");

        for (String elem : instructions) {
            elem = elem.replaceAll("//(.*)$", "");

            ArrayList<String> ligne = new ArrayList<>(Arrays.asList(elem.split("\\s+")));
            String type;

            if (ligne.get(0).equals("")) {
                ligne.remove(0);
            }

            if (ligne.get(0).toUpperCase().equals("B"))
                ligne.set(0, "BAL");

            for (int i = 1; i < ligne.size(); i++) {
                if (ligne.get(i).contains("[")) {
                    try {
                        ligne.set(i, ligne.get(i).concat(ligne.get(i + 1)));
                        ligne.remove(i + 1);
                    } catch (IndexOutOfBoundsException e) {
                        break;
                    }
                }
            }

            type = ligne.get(ligne.size() - 1).contains("r") ? "REGISTRE" : "IMMEDIAT";

            Optional<String> imm_matche = Optional.empty();
            Optional<String> binary_imm_matches = Optional.empty();
            List<String> reg_matches;
            List<String> binary_reg_matches = new ArrayList<>();
            String res;

            if (instruction_all.containsKey(new AbstractMap.SimpleEntry<>(ligne.get(0).toUpperCase(), type))) {
                var imm_matches = Pattern.compile("#([0-9]+)")
                        .matcher(elem)
                        .results()
                        .map(x -> x.group(1))
                        .collect(Collectors.toList());

                reg_matches = Pattern.compile("r([0-9]+)")
                        .matcher(elem)
                        .results()
                        .map(x -> x.group(1))
                        .collect(Collectors.toList());

                if (!imm_matches.isEmpty())
                    imm_matche = Optional.of(imm_matches.get(0));

                if (imm_matche.isPresent()) {
                    binary_imm_matches = Optional.of(String.format("%" + instruction_all.get(
                                    new AbstractMap.SimpleEntry<>(ligne.get(0).toUpperCase(), type)).getValue() + "s",
                            Integer.toBinaryString(Integer.parseInt(imm_matche.get()))).replaceAll(" ", "0"));
                }

                binary_reg_matches = reg_matches.stream()
                        .map(Integer::parseInt)
                        .map(Integer::toBinaryString)
                        .map(x -> String.format("%3s", x).replaceAll(" ", "0"))
                        .collect(Collectors.toList());
            } else if (ligne.get(0).toUpperCase().charAt(0) == 'B') {
                imm_matche = Optional.of(Integer.toString((label.get(ligne.get(1).toUpperCase())) - pc - 3));

                if (Integer.parseInt(imm_matche.get()) < 0)
                    binary_imm_matches = Optional.of(Integer.toBinaryString(Integer.parseInt(imm_matche.get())).substring(24));
                else
                    binary_imm_matches = Optional.of(String.format("%8s", Integer.toBinaryString(Integer.parseInt(imm_matche.get()))).replaceAll(" ", "0"));
                if (conditions.containsKey(ligne.get(0).substring(1).toUpperCase()))
                    binary_reg_matches.add(conditions.get(ligne.get(0).substring(1).toUpperCase()));
                else
                    throw new IllegalStateException("Wrong instruction !");
            } else
                throw new IllegalStateException("Wrong instruction !");

            try {
                res = instruction_all.get(new AbstractMap.SimpleEntry<>(ligne.get(0).toUpperCase(), type)).getKey();
            } catch (NullPointerException e) {
                res = instruction_all.get(new AbstractMap.SimpleEntry<>(ligne.get(0).substring(0, 1).toUpperCase(), type)).getKey();
            }
            if (res.equals("0100001101"))
                binary_reg_matches.remove(0);
            else
                Collections.reverse(binary_reg_matches);

            if (res.equals("00001") || res.equals("0001110") || res.equals("0001111") || res.equals("00000") || res.equals("00010")) {
                if (binary_imm_matches.isPresent())
                    res = res.concat(binary_imm_matches.get());
                for (String binary_reg_match : binary_reg_matches) {
                    res = res.concat(binary_reg_match);
                }
            } else {
                for (String binary_reg_match : binary_reg_matches) {
                    res = res.concat(binary_reg_match);
                }
                if (binary_imm_matches.isPresent())
                    res = res.concat(binary_imm_matches.get());
            }
            while (res.length() < 16)
                res = res.concat("0");
            while (res.length() > 16)
                res = res.substring(0, 16);

            String res_hexa = Integer.toHexString(Integer.parseInt(res, 2));
            res_hexa = String.format("%4s", res_hexa).replaceAll(" ", "0");

            bw.write(res_hexa + " ");
            pc++;
        }
        bw.close();
    }
}
