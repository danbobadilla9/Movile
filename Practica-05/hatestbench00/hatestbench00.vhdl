library ieee;
use ieee.std_logic_1164.all;
entity hatestbench00 is
end hatestbench00;

architecture hatestbench0 of hatestbench00 is
	component ha00
		port(
			A0: in STD_LOGIC;
			B0: in std_logic;
			S0: out std_logic;
			C0: out std_logic
		);
	end component;
	Signal sA0:  std_logic;
	Signal sB0:  std_logic;
	Signal sS0:  std_logic;
	Signal sC0:  std_logic;
begin
	HAS00: ha00 port map(
		A0 => sA0,
		B0 => sB0,
		S0 => sS0,
		C0 => sC0
	);
	estimulos: process
		begin
			---------------------
				sA0 <= '1';
				sB0 <= '1';
				wait for 20 ns;
			---------------------
				sA0 <= '1';
				sB0 <= '0';
				wait for 20 ns;
			---------------------
				sA0 <= '0';
				sB0 <= '1';
				wait for 20 ns;
			---------------------
				sA0 <= '0';
				sB0 <= '0';
				wait for 20 ns;
			---------------------
			wait;
		end process;
end hatestbench0;